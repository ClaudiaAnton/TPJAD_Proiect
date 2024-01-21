package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "UTILIZATOR")
public class Utilizator {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "UTILIZATOR_ID_SQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "UTILIZATORNAME")
    private String utilizatorName;

    @Column(name = "PASSWORD")
    private String password;

}
