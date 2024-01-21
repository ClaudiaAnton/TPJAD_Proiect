package com.example.demo.service;

import com.example.demo.domain.Punctaj;
import com.example.demo.dto.ValidareTestDto;
import com.example.demo.dto.ValidareTestTotalDto;
import com.example.demo.enums.BusinessErrorCode;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.repository.IntrebareRepo;
import com.example.demo.repository.PunctajRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PunctajService {

  private final PunctajRepo punctajRepo;
  private final IntrebareRepo intrebareRepo;
  private final UtilizatorService utilizatorService;

  public ValidareTestDto testPassed(Long cursId, Long utilizatorId) {
    var punctaje = punctajRepo.findAllByCursIdAndUtilizatorId(cursId, utilizatorId);
    if (!punctaje.isEmpty()) {

      int sumaPuncte = punctaje
          .stream()
          .mapToInt(Punctaj::getPunct)
          .sum();
      return new ValidareTestDto(sumaPuncte
          >= punctaje.size() * 50 / 100 + 1, sumaPuncte,
          utilizatorService.calculateTotalScore(utilizatorId));
    }
    throw new BusinessException(List.of(BusinessException.BusinessExceptionElement.builder()
        .errorCode(BusinessErrorCode.TEST_NOT_ANSWERED_YET).build()));
  }


  public ValidareTestTotalDto allTestsPassed(Long utilizatorId) {
    var punctaje = punctajRepo.findAllByUtilizatorId(utilizatorId);
    // Verifica daca utilizatorul a raspuns la toate intrebarile de la toate cursurile
    if (punctaje.size() == intrebareRepo.findAll().size()) {
      int scorTotal = utilizatorService.calculateTotalScore(utilizatorId);
      int maxScorPosibil = intrebareRepo.findAll().size();

        if (scorTotal >= maxScorPosibil * 80 / 100 + 1) {
            return new ValidareTestTotalDto(true, scorTotal, maxScorPosibil);
        }

      return new ValidareTestTotalDto(false, scorTotal, maxScorPosibil);
    }
    throw new BusinessException(List.of(BusinessException.BusinessExceptionElement.builder()
        .errorCode(BusinessErrorCode.NOT_ALL_TESTS_HAVE_BEEN_COMPLETED).build()));
  }
}
