package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.example.demo.api.PunctajApi;
import com.example.demo.dto.ValidareTestDto;
import com.example.demo.dto.ValidareTestTotalDto;
import com.example.demo.service.PunctajService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
class PunctajApiTest {

  @InjectMocks
  PunctajApi punctajApi;
  @Mock
  PunctajService punctajService;
  private MockMvc mockMvc;

  @BeforeEach
  void setUpMockMvc() {
    this.mockMvc = standaloneSetup(punctajApi)
        .setControllerAdvice(punctajService)
        .build();
  }

  @Test
  void validareTestTest() throws Exception {
    when(punctajService.testPassed(1L,1L)).thenReturn(new ValidareTestDto(true,5,5));
    mockMvc.perform(
            get("/punctaj/validare-test/{utilizatorId}/{cursId}", 1L, 1L))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(content().json(
                """
                     {
                       "testTrecut": true,
                       "puncteObtinute": 5,
                       "punctajTotal": 5
                     }
                    """
            )
        );
  }

  @Test
  void validareTesteTotalTest() throws Exception {
    when(punctajService.allTestsPassed(1L)).thenReturn(new ValidareTestTotalDto(true,5,5));
    mockMvc.perform(
            get("/punctaj/validare-teste-total/{utilizatorId}",  1L))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(content().json(
                """
                     {
                       "testeTrecute": true,
                       "scorTotal": 5,
                       "maxScorPosibil": 5
                     }
                    """
            )
        );
  }

}


