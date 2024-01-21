package com.example.demo.mapper;

import com.example.demo.domain.Intrebare;
import com.example.demo.dto.GetIntrebareDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-21T18:10:25+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class IntrebareMapperImpl implements IntrebareMapper {

    @Override
    public GetIntrebareDto intrebareToGetIntrebareDto(Intrebare intrebare) {
        if ( intrebare == null ) {
            return null;
        }

        GetIntrebareDto getIntrebareDto = new GetIntrebareDto();

        getIntrebareDto.setId( intrebare.getId() );
        getIntrebareDto.setText( intrebare.getText() );
        getIntrebareDto.setRaspuns1( intrebare.getRaspuns1() );
        getIntrebareDto.setRaspuns2( intrebare.getRaspuns2() );
        getIntrebareDto.setRaspuns3( intrebare.getRaspuns3() );
        getIntrebareDto.setRaspuns4( intrebare.getRaspuns4() );

        return getIntrebareDto;
    }
}
