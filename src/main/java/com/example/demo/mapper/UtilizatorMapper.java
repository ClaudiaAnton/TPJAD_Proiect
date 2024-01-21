package com.example.demo.mapper;

import com.example.demo.domain.Utilizator;
import com.example.demo.dto.UtilizatorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilizatorMapper {
  UtilizatorDto entityToDto(Utilizator utilizator);
}
