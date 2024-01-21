package com.example.demo.dto;

import lombok.Data;

@Data
public class ValidareTestTotalDto {
    private boolean testeTrecute;
    private int scorTotal;
    private int maxScorPosibil;

    public ValidareTestTotalDto(boolean testeTrecute, int scorTotal, int maxScorPosibil) {
        this.testeTrecute = testeTrecute;
        this.scorTotal = scorTotal;
        this.maxScorPosibil = maxScorPosibil;
    }
}
