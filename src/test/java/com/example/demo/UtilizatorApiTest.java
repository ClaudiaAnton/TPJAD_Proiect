package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.example.demo.api.UtilizatorApi;
import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.UtilizatorDto;
import com.example.demo.service.UtilizatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
class UtilizatorApiTest {

  @InjectMocks
  UtilizatorApi utilizatorApi;
  @Mock
  UtilizatorService utilizatorService;
  private MockMvc mockMvc;

  @BeforeEach
  void setUpMockMvc() {
    this.mockMvc = standaloneSetup(utilizatorApi)
        .setControllerAdvice(utilizatorService)
        .build();
  }

  @Test
  void loginTest() throws Exception {
    var loign = new LoginResponseDto(new UtilizatorDto(1L, "a", "a"), 10, 1L);
    when(utilizatorService.loginUser(any())).thenReturn(loign);
    mockMvc.perform(
            post("/utilizator/login")
                .contentType(APPLICATION_JSON)
                .content("""
                    {
                    "utilizatorName": "a",
                     "password": "a"
                    }
                                        """
                )
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(content().json(
                """
                    {
                        "utilizator": {
                            "id": 1,
                            "utilizatorName": "a",
                            "password": "a"
                        },
                        "scorTotal": 10,
                        "idCursCurent": 1
                    }
                                        """
            )
        );
  }

}
