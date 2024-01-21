package com.example.demo.dto;

import com.example.demo.domain.Curs;
import com.example.demo.domain.Intrebare;
import com.example.demo.domain.Utilizator;
import lombok.Data;

import javax.persistence.*;

@Data
public class PunctajDto {

    private Long id;


    private UtilizatorDto utilizator;


    private Long cursId;

    private Long intrebareId;


    private int punct;
}
