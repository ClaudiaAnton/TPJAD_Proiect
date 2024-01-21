package com.example.demo.mapper;

import com.example.demo.domain.Punctaj;
import com.example.demo.dto.PunctajDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PunctajMapper {
    PunctajDto entityToDto(Punctaj punctaj);
    Punctaj dtoToEntity(PunctajDto punctajDto);

}
