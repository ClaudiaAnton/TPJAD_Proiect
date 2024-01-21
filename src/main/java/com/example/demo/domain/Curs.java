package com.example.demo.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "CURS")
public class Curs {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "CURS_ID_SQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DENUMIRE")
    private String denumire;

    @Column(name = "TITLU")
    private String titlu;

    @Column(name = "DESCRIERE")
    private String descriere;
}
