package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;

@Data
@AllArgsConstructor
public class UtilizatorDto {
    private Long id;
    private String utilizatorName;

    private String password;

}
