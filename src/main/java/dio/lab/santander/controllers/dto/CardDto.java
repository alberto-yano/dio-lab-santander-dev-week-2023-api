package dio.lab.santander.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dio.lab.santander.domain.Card;

import java.math.BigDecimal;

@JsonInclude( JsonInclude.Include.NON_EMPTY )
public record CardDto( Long id,
                       String number,
                       BigDecimal limit ) {

  public CardDto( Card model ) {
    this( model.id(), model.number(), model.limit() );
  }

  public Card toModel() {
    return new Card( this.id, this.number, this.limit );
  }

}
