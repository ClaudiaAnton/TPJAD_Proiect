package com.example.demo.service;

import com.example.demo.domain.Punctaj;
import com.example.demo.dto.GetIntrebareDto;
import com.example.demo.dto.RaspunsDto;
import com.example.demo.enums.BusinessErrorCode;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.mapper.IntrebareMapper;
import com.example.demo.repository.CursRepo;
import com.example.demo.repository.IntrebareRepo;
import com.example.demo.repository.PunctajRepo;
import com.example.demo.repository.UtilizatorRepo;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IntrebareService {

  private final IntrebareRepo intrebareRepo;
  private final PunctajRepo punctajRepo;
  private final UtilizatorRepo utilizatorRepo;
  private final CursRepo cursRepo;
  private final IntrebareMapper intrebareMapper;

  private boolean isItNew(Long userId, Long cursId, Long intrebareId) {
    return punctajRepo.findAll().stream()
        .noneMatch(punctaj -> punctaj.getUtilizator().getId().equals(userId)
            && punctaj.getCurs().getId().equals(cursId)
            && punctaj.getIntrebare().getId().equals(intrebareId));
  }

  @Transactional
  public GetIntrebareDto getNextQuestion(Long idCurs, Long idUser) {
    return intrebareRepo.findAll()
        .stream()
        .filter(intrebare -> intrebare.getCurs().getId().equals(idCurs))
        .filter(intrebare -> isItNew(idUser, idCurs, intrebare.getId()))
        .map(intrebareMapper::intrebareToGetIntrebareDto)
        .findFirst()
        .orElse(new GetIntrebareDto());
  }

  public void verifyIfAnswerCorrect(RaspunsDto dto) {
    var optionalIntrebare = intrebareRepo.findById(dto.getIntrebareId()).orElseThrow(
        () -> new BusinessException(List.of(BusinessException.BusinessExceptionElement.builder()
            .errorCode(BusinessErrorCode.INTREBARE_NOT_FOUND).build())));

    var utilizator = utilizatorRepo.findById(dto.getUtilizatorId()).orElseThrow(
        () -> new BusinessException(List.of(BusinessException.BusinessExceptionElement.builder()
            .errorCode(BusinessErrorCode.USER_NOT_FOUND).build())));

    if (punctajRepo.findFirstByUtilizatorAndCursAndIntrebare(utilizator,
        optionalIntrebare.getCurs(), optionalIntrebare).isPresent()) {
      throw new BusinessException(List.of(BusinessException.BusinessExceptionElement.builder()
          .errorCode(BusinessErrorCode.INTREBARE_ALREADY_ANSWERED).build()));
    }

    var punctajIntrebare = new Punctaj();
    punctajIntrebare.setUtilizator(utilizator);
    punctajIntrebare.setCurs(optionalIntrebare.getCurs());
    punctajIntrebare.setIntrebare(optionalIntrebare);

    if (optionalIntrebare.getRaspuns().equalsIgnoreCase(dto.getRaspuns())) {
      punctajIntrebare.setPunct(1);
    } else {
      punctajIntrebare.setPunct(0);
    }
    punctajRepo.save(punctajIntrebare);
  }

  public void refreshTest(Long userId, Long cursId) {
    var user = utilizatorRepo.findById(userId).orElseThrow(
        () -> new BusinessException(List.of(BusinessException.BusinessExceptionElement.builder()
            .errorCode(BusinessErrorCode.USER_NOT_FOUND).build())));
    var curs = cursRepo.findById(cursId).orElseThrow(
        () -> new BusinessException(List.of(BusinessException.BusinessExceptionElement.builder()
            .errorCode(BusinessErrorCode.CURS_NOT_FOUND).build())));

    var intrebari = intrebareRepo.findAllByCurs(curs).orElseThrow(
        () -> new BusinessException(List.of(BusinessException.BusinessExceptionElement.builder()
            .errorCode(BusinessErrorCode.CURS_FARA_INTREBARI).build())));

    intrebari.forEach(
        intrebare -> punctajRepo.findFirstByUtilizatorAndCursAndIntrebare(user, curs, intrebare)
            .ifPresent(punctajRepo::delete));
  }
}
