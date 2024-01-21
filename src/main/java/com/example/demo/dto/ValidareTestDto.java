package com.example.demo.dto;

import lombok.Data;

@Data
public class ValidareTestDto {
    private boolean testTrecut;
    private int puncteObtinute;
    private int punctajTotal;

    public ValidareTestDto(boolean testTrecut, int puncteObtinute, int punctajTotal) {
        this.testTrecut = testTrecut;
        this.puncteObtinute = puncteObtinute;
        this.punctajTotal=punctajTotal;
    }
}
