package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RaspunsDto {
    Long intrebareId;
    Long utilizatorId;

    String raspuns;
@JsonCreator
    public RaspunsDto(@JsonProperty("intrebareId") Long intrebareId,
                      @JsonProperty("utilizatorId") Long utilizatorId,
                      @JsonProperty("raspuns") String raspuns) {
    this.raspuns = raspuns;
    this.utilizatorId = utilizatorId;
    this.intrebareId = intrebareId;
}
}
