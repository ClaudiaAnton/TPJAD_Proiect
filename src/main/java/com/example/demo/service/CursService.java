package com.example.demo.service;

import com.example.demo.dto.GetCursDto;
import com.example.demo.enums.BusinessErrorCode;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.BusinessException.BusinessExceptionElement;
import com.example.demo.repository.CursRepo;
import com.example.demo.repository.IntrebareRepo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CursService {
  private final CursRepo cursRepo;
  private final IntrebareRepo intrebareRepo;
  private final IntrebareService intrebareService;

  @Transactional
  public GetCursDto getCurs(Long idCurs, Long idUser) {
    return cursRepo.findById(idCurs)
        .map(curs -> new GetCursDto(curs,
            intrebareRepo.findAll()
                .stream()
                .filter(intrebare -> intrebare.getCurs().getId().equals(idCurs))
                .count(), intrebareService.getNextQuestion(curs.getId(), idUser).getText() == null
        ))
        .orElseThrow(() -> new BusinessException(List.of(BusinessExceptionElement.builder()
            .errorCode(BusinessErrorCode.CURS_NOT_FOUND).build()))
        );
  }
}
