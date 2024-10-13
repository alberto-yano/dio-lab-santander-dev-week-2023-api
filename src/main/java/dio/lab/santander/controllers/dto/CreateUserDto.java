package dio.lab.santander.controllers.dto;

import dio.lab.santander.domain.Account;
import dio.lab.santander.domain.Card;
import dio.lab.santander.domain.User;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public record CreateUserDto( String name,
                             CreateAccountDto account,
                             CreateCardDto card,
                             List<FeatureDto> features,
                             List<NewsDto> news ) {

  public User toModel() {
    return new User( null, this.name, ofNullable( this.account ).map( CreateAccountDto::toModel ).orElse( null ),
                     ofNullable( this.card ).map( CreateCardDto::toModel ).orElse( null ),
                     ofNullable( this.features ).orElse( emptyList() ).stream().map( FeatureDto::toModel ).collect(
                         toList() ),
                     ofNullable( this.news ).orElse( emptyList() ).stream().map( NewsDto::toModel ).collect(
                         toList() ) );
  }

  public record CreateAccountDto( String number,
                                  String agency,
                                  BigDecimal balance,
                                  BigDecimal limit ) {

    public Account toModel() {
      return new Account( null, this.number, this.agency, this.balance, this.limit );
    }

  }

  public record CreateCardDto( String number,
                               BigDecimal limit ) {

    public Card toModel() {
      return new Card( null, this.number, this.limit );
    }

  }

}
