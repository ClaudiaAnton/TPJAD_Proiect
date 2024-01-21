package com.example.demo.domain;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "PUNCTAJ")
public class Punctaj implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "PUNCTAJ_ID_SQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "UTILIZATOR_ID")
    private Utilizator utilizator;

    @OneToOne
    @JoinColumn(name = "CURS_ID")
    private Curs curs;

    @OneToOne
    @JoinColumn(name = "INTREBARE_ID")
    private Intrebare intrebare;

    @Column(name = "PUNCT")
    private int punct;


}
