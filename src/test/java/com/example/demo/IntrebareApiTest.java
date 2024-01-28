package com.example.demo;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.example.demo.api.IntrebareApi;
import com.example.demo.dto.GetIntrebareDto;
import com.example.demo.dto.RaspunsDto;
import com.example.demo.service.IntrebareService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
class IntrebareApiTest {

  @InjectMocks
  private IntrebareApi intrebareApi;
  @Mock
  private IntrebareService intrebareService;
  private MockMvc mockMvc;

  @BeforeEach
  void setUpMockMvc() {
    this.mockMvc = standaloneSetup(intrebareApi)
        .setControllerAdvice(intrebareService)
        .build();
  }

  @Test
  void getNextQuestionTest() throws Exception {
    var intrebare = new GetIntrebareDto();
    intrebare.setId(1L);
    intrebare.setText("a");
    intrebare.setRaspuns1("a");
    intrebare.setRaspuns2("a");
    intrebare.setRaspuns3("a");
    intrebare.setRaspuns4("a");
    when(intrebareService.getNextQuestion(1L, 1L)).thenReturn(intrebare);

    mockMvc.perform(
            get("/intrebare/{userId}/{cursId}", 1L, 1L))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(content().json(
                """
                     {
                       "id": 1,
                       "text": "a",
                       "raspuns1": "a",
                       "raspuns2": "a",
                       "raspuns3": "a",
                       "raspuns4": "a"
                     }
                    """
            )
        );
  }

  @Test
  void updateIntrebareTest() throws Exception {
    doNothing().when(intrebareService).verifyIfAnswerCorrect(new RaspunsDto(1L, 1L, "a"));

    mockMvc.perform(
            post("/intrebare/update")
                .contentType(APPLICATION_JSON)
                .content(
                    """
                        {
                              "intrebareId": 1,
                              "utilizatorId": 1,
                              "raspuns": "a"
                        }
                         """
                )
        )
        .andExpect(status().isNoContent());
  }

  @Test
  void refreshTestTest() throws Exception {
    doNothing().when(intrebareService).refreshTest(1L, 1L);

    mockMvc.perform(
            delete("/intrebare/refresh/{userId}/{cursId}", 1L, 1L)
        )
        .andExpect(status().isNoContent());
  }

}
