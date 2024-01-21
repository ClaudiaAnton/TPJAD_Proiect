package com.example.demo.api;

import com.example.demo.dto.GetCursDto;
import com.example.demo.dto.GetIntrebareDto;
import com.example.demo.service.CursService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/curs")
public class CursApi {

  private final CursService cursService;

  @GetMapping("/{userId}/{cursId}")
  public ResponseEntity<GetCursDto> getCurs(@PathVariable Long userId, @PathVariable Long cursId) {
    return ResponseEntity.ok(cursService.getCurs(cursId, userId));
  }

}
