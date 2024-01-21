package com.example.demo.mapper;

import com.example.demo.domain.Utilizator;
import com.example.demo.dto.UtilizatorDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-21T18:10:24+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class UtilizatorMapperImpl implements UtilizatorMapper {

    @Override
    public UtilizatorDto entityToDto(Utilizator utilizator) {
        if ( utilizator == null ) {
            return null;
        }

        Long id = null;
        String utilizatorName = null;
        String password = null;

        id = utilizator.getId();
        utilizatorName = utilizator.getUtilizatorName();
        password = utilizator.getPassword();

        UtilizatorDto utilizatorDto = new UtilizatorDto( id, utilizatorName, password );

        return utilizatorDto;
    }
}
