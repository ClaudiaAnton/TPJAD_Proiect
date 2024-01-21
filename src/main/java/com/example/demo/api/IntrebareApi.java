package com.example.demo.api;

import com.example.demo.dto.GetIntrebareDto;
import com.example.demo.dto.RaspunsDto;
import com.example.demo.service.IntrebareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/intrebare")
public class IntrebareApi {

  private final IntrebareService intrebareService;

  @GetMapping("/{userId}/{cursId}")
  public ResponseEntity<GetIntrebareDto> getNextQuestion(@PathVariable Long userId,
      @PathVariable Long cursId) {
    return ResponseEntity.ok(intrebareService.getNextQuestion(cursId, userId));
  }

  /*
   * input: un obiect continand intrebareId,utilizatorId, raspuns(String)
   * output: punctajul obtinut de utilizatorul cu utilizatorId, la intrebarea cu intrebareId cu raspunsul dat
   * */
  @PostMapping("/update")
  public ResponseEntity<Void> updateIntrebare(@RequestBody RaspunsDto dto) {
    intrebareService.verifyIfAnswerCorrect(dto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/refresh/{userId}/{cursId}")
  public ResponseEntity<Void> refreshTest(@PathVariable Long userId, @PathVariable Long cursId) {
    intrebareService.refreshTest(userId, cursId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
