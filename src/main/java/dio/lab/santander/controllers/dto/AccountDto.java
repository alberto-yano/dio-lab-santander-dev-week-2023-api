package dio.lab.santander.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dio.lab.santander.domain.Account;

import java.math.BigDecimal;

@JsonInclude( JsonInclude.Include.NON_EMPTY )
public record AccountDto( Long id,
                          String number,
                          String agency,
                          BigDecimal balance,
                          BigDecimal limit ) {

  public AccountDto( Account model ) {
    this( model.id(), model.number(), model.agency(), model.balance(), model.limit() );
  }

  public Account toModel() {
    return new Account( this.id, this.number, this.agency, this.balance, this.limit );
  }

}
