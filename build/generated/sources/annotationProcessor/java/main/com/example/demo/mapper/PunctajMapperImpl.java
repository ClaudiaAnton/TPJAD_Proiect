package com.example.demo.mapper;

import com.example.demo.domain.Punctaj;
import com.example.demo.domain.Utilizator;
import com.example.demo.dto.PunctajDto;
import com.example.demo.dto.UtilizatorDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-21T18:10:25+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class PunctajMapperImpl implements PunctajMapper {

    @Override
    public PunctajDto entityToDto(Punctaj punctaj) {
        if ( punctaj == null ) {
            return null;
        }

        PunctajDto punctajDto = new PunctajDto();

        punctajDto.setId( punctaj.getId() );
        punctajDto.setUtilizator( utilizatorToUtilizatorDto( punctaj.getUtilizator() ) );
        punctajDto.setPunct( punctaj.getPunct() );

        return punctajDto;
    }

    @Override
    public Punctaj dtoToEntity(PunctajDto punctajDto) {
        if ( punctajDto == null ) {
            return null;
        }

        Punctaj punctaj = new Punctaj();

        punctaj.setId( punctajDto.getId() );
        punctaj.setUtilizator( utilizatorDtoToUtilizator( punctajDto.getUtilizator() ) );
        punctaj.setPunct( punctajDto.getPunct() );

        return punctaj;
    }

    protected UtilizatorDto utilizatorToUtilizatorDto(Utilizator utilizator) {
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

    protected Utilizator utilizatorDtoToUtilizator(UtilizatorDto utilizatorDto) {
        if ( utilizatorDto == null ) {
            return null;
        }

        Utilizator utilizator = new Utilizator();

        utilizator.setId( utilizatorDto.getId() );
        utilizator.setUtilizatorName( utilizatorDto.getUtilizatorName() );
        utilizator.setPassword( utilizatorDto.getPassword() );

        return utilizator;
    }
}
