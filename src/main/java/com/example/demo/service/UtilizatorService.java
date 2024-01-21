package com.example.demo.service;

import com.example.demo.domain.Curs;
import com.example.demo.domain.Punctaj;
import com.example.demo.domain.Utilizator;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.UtilizatorDto;

import com.example.demo.enums.BusinessErrorCode;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.BusinessException.BusinessExceptionElement;
import com.example.demo.mapper.UtilizatorMapper;
import com.example.demo.repository.PunctajRepo;
import com.example.demo.repository.UtilizatorRepo;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class UtilizatorService {

  private final UtilizatorRepo utilizatorRepo;
  private final UtilizatorMapper utilizatorMapper;

  private final PunctajRepo punctajRepo;

  public LoginResponseDto loginUser(LoginRequestDto dto) {
    var optionalUtilizator = utilizatorRepo.findUtilizatorByUtilizatorName(dto.getUtilizatorName());

    if (optionalUtilizator.isPresent()) {

      var utilizator = optionalUtilizator.get();

      if (utilizator.getPassword().equals(dto.getPassword())) {

        UtilizatorDto utilizatorDto = utilizatorMapper.entityToDto(utilizator);

        return new LoginResponseDto(utilizatorDto, calculateTotalScore(utilizator.getId()),
            getCurrentCurs(utilizator));
      } else {
        throw new BusinessException(List.of(BusinessExceptionElement.builder()
            .errorCode(BusinessErrorCode.PAROLA_INCORECTA)
            .build()));
      }
    } else {
      throw new BusinessException(List.of(BusinessExceptionElement.builder()
          .errorCode(BusinessErrorCode.PAROLA_INCORECTA)
          .build()));
    }
  }

  public int calculateTotalScore(Long utilizatorId) {
    List<Punctaj> punctaje = this.punctajRepo.findAllByUtilizatorId(utilizatorId);
    if (!punctaje.isEmpty()) {
      return punctaje
              .stream()
              .mapToInt(Punctaj::getPunct)
              .sum();
    }
    return 0;
  }

  public Long getCurrentCurs(Utilizator utilizator) {
    Optional<List<Punctaj>> optionalPunctaje = this.punctajRepo.findAllByUtilizator(utilizator);
    if (optionalPunctaje.isPresent()) {
      List<Punctaj> punctaje = optionalPunctaje.get();
      return punctaje
          .stream()
          .map(Punctaj::getCurs)
          .collect(groupingBy(Curs::getId))
          .entrySet()
          .stream()
          .mapToLong(Map.Entry::getKey)
          .max()
          .orElse(1L);
    }
    return -1L;
  }
}

