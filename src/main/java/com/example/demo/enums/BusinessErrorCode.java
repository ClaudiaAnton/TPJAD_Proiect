package com.example.demo.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = Shape.OBJECT)
@Getter
public enum BusinessErrorCode {

  PAROLA_INCORECTA("EII01400", "Parola gresita"),
  USER_NOT_FOUND("EII02400", "Utilizatorul nu a fost găsit"),
  CURS_NOT_FOUND("EII03400", "Curs inexistent"),
  INTREBARE_NOT_FOUND("EII04400", "Intrebare inexistenta"),
  INTREBARE_ALREADY_ANSWERED("EII05400", "Aceasta intrebare a primit deja un raspuns"),
  CURS_FARA_INTREBARI("EII06400", "Acest curs nu are intrebari"),
  TEST_NOT_ANSWERED_YET("EII07400", "Acest test nu a fost inceput"),
  PUNCTAJ_NOT_FOUND("EII08400", "Punctaj inexistenta"),
  NOT_ALL_TESTS_HAVE_BEEN_COMPLETED("EII09400", "Utilizatorul nu a completat toate testele");

  private final String errorCode;
  private final String devMsg;
  private final HttpStatus status;

  BusinessErrorCode(String errorCode, String devMsg) {
    this.errorCode = errorCode;
    this.devMsg = devMsg;
    this.status = HttpStatus.resolve(Integer.parseInt(errorCode.substring(errorCode.length() - 3)));
  }
}
