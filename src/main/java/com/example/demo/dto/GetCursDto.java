package com.example.demo.dto;

import com.example.demo.domain.Curs;
import lombok.Data;

@Data
public class GetCursDto {

    private String denumire;
    private String title;
    private String descriere;
    private boolean rezolvat;
    private Long nrIntrebari;

    public GetCursDto(Curs curs, Long nrIntrebari, Boolean rezolvat) {
        this.denumire = curs.getDenumire();
        this.title = curs.getTitlu();
        this.descriere = curs.getDescriere();
        this.rezolvat = rezolvat;
        this.nrIntrebari = nrIntrebari;
    }
}
