package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.example.demo.domain.Intrebare;
import com.example.demo.domain.Punctaj;
import com.example.demo.dto.ValidareTestDto;
import com.example.demo.dto.ValidareTestTotalDto;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.repository.IntrebareRepo;
import com.example.demo.repository.PunctajRepo;
import com.example.demo.service.PunctajService;
import com.example.demo.service.UtilizatorService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PunctajServiceTest {

  @InjectMocks
  PunctajService punctajService;
  @Mock
  PunctajRepo punctajRepo;
  @Mock
  IntrebareRepo intrebareRepo;
  @Mock
  UtilizatorService utilizatorService;

  @Test
  void testPassedTest() {
    assertThrows(BusinessException.class, () -> punctajService.testPassed(1L, 1L));
    var punctaj1 = new Punctaj();
    punctaj1.setPunct(5);
    var punctaj2 = new Punctaj();
    punctaj2.setPunct(3);
    when(punctajRepo.findAllByCursIdAndUtilizatorId(1L, 1L)).thenReturn(
        List.of(punctaj1, punctaj2));
    when(utilizatorService.calculateTotalScore(1L)).thenReturn(10);
    var rez = new ValidareTestDto(true, 8, 10);
    assertEquals(rez, punctajService.testPassed(1L, 1L));
  }

  @Test
  void allTestsPassedTest(){
    var punctaj1 = new Punctaj();
    punctaj1.setPunct(1);
    var punctaj2 = new Punctaj();
    punctaj2.setPunct(1);
    when(punctajRepo.findAllByUtilizatorId(1L)).thenReturn(List.of(punctaj1, punctaj2, punctaj1));
    when(intrebareRepo.findAll()).thenReturn(List.of(new Intrebare()));
    assertThrows(BusinessException.class, () -> punctajService.allTestsPassed(1L));
    when(utilizatorService.calculateTotalScore(1L)).thenReturn(2);
    when(intrebareRepo.findAll()).thenReturn(List.of(new Intrebare(), new Intrebare(), new Intrebare()));
    var rez = new ValidareTestTotalDto(false, 2, 3);
    assertEquals(rez, punctajService.allTestsPassed(1L));

    when(utilizatorService.calculateTotalScore(1L)).thenReturn(3);
    rez = new ValidareTestTotalDto(true, 3, 3);
    assertEquals(rez, punctajService.allTestsPassed(1L));

  }

}
