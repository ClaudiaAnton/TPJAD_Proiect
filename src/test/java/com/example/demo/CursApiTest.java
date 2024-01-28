package com.example.demo;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.example.demo.api.CursApi;
import com.example.demo.domain.Curs;
import com.example.demo.dto.GetCursDto;
import com.example.demo.service.CursService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
class CursApiTest {
  @InjectMocks
  private CursApi cursApi;
  @Mock
  private CursService cursService;
  private MockMvc mockMvc;

  @BeforeEach
  void setUpMockMvc() {
    this.mockMvc = standaloneSetup(cursApi)
        .setControllerAdvice(cursService)
        .build();
  }

  @Test
  void getCursTest() throws Exception {
    var curs=new Curs();
    curs.setDenumire("a");
    curs.setTitlu("a");
    curs.setDescriere("a");
    when(cursService.getCurs(1L ,1L)).thenReturn(new GetCursDto(curs,5L,true));

    mockMvc.perform(
            get("/intrebare/{userId}/{cursId}",  1L,1L))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(content().json(
                """
                   {
                     "denumire": "a",
                     "title": "a",
                     "descriere": "a",
                     "rezolvat": true,
                     "nrIntrebari": 5
                   }
                  """
            )
        );
  }


}
