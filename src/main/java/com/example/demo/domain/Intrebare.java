package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "INTREBARE")
public class Intrebare {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "INTREBARE_ID_SQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "CURS_ID")
    private Curs curs;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "RASPUNS1")
    private String raspuns1;

    @Column(name = "RASPUNS2")
    private String raspuns2;

    @Column(name = "RASPUNS3")
    private String raspuns3;

    @Column(name = "RASPUNS4")
    private String raspuns4;

    @Column(name = "RASPUNS_CORECT")
    private String raspuns;
}
