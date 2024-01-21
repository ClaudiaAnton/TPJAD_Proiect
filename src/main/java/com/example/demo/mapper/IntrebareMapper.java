package com.example.demo.mapper;

import com.example.demo.domain.Intrebare;
import com.example.demo.dto.GetIntrebareDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IntrebareMapper {
  GetIntrebareDto intrebareToGetIntrebareDto(Intrebare intrebare);
}
