package com.example.demo.api;

import com.example.demo.dto.ValidareTestDto;
import com.example.demo.dto.ValidareTestTotalDto;
import com.example.demo.service.PunctajService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/punctaj")
public class PunctajApi {
    private final PunctajService punctajService;

    @GetMapping("/validare-test/{utilizatorId}/{cursId}")
    public ResponseEntity<ValidareTestDto> validareTest(@PathVariable Long  utilizatorId, @PathVariable Long cursId) {
        return ResponseEntity.ok(punctajService.testPassed(cursId, utilizatorId));
    }

    @GetMapping("/validare-teste-total/{utilizatorId}")
    public ResponseEntity<ValidareTestTotalDto> validareTesteTotal(@PathVariable Long utilizatorId) {
        return ResponseEntity.ok(punctajService.allTestsPassed(utilizatorId));
    }

}
