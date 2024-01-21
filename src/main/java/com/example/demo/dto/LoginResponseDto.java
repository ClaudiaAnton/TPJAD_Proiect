package com.example.demo.dto;

import lombok.Data;

@Data
public class LoginResponseDto {

    UtilizatorDto utilizator;
    int scorTotal;
    Long idCursCurent;


    public LoginResponseDto(UtilizatorDto utilizator, int scorTotal, Long idCursCurent) {
        this.utilizator = utilizator;
        this.scorTotal = scorTotal;
        this.idCursCurent = idCursCurent;
    }
}
