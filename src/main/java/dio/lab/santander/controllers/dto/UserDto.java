package dio.lab.santander.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dio.lab.santander.domain.User;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@JsonInclude( JsonInclude.Include.NON_EMPTY )
public record UserDto( Long id,
                       String name,
                       AccountDto account,
                       CardDto card,
                       List<FeatureDto> features,
                       List<NewsDto> news ) {

  public UserDto( User model ) {
    this( model.id(), model.name(), ofNullable( model.account() ).map( AccountDto::new ).orElse( null ),
          ofNullable( model.card() ).map( CardDto::new ).orElse( null ),
          ofNullable( model.features() ).orElse( emptyList() ).stream().map( FeatureDto::new ).collect( toList() ),
          ofNullable( model.news() ).orElse( emptyList() ).stream().map( NewsDto::new ).collect( toList() ) );
  }

  public User toModel() {
    return new User( this.id, this.name, ofNullable( this.account ).map( AccountDto::toModel ).orElse( null ),
                     ofNullable( this.card ).map( CardDto::toModel ).orElse( null ),
                     ofNullable( this.features ).orElse( emptyList() ).stream().map( FeatureDto::toModel ).collect(
                         toList() ),
                     ofNullable( this.news ).orElse( emptyList() ).stream().map( NewsDto::toModel ).collect(
                         toList() ) );
  }

}

