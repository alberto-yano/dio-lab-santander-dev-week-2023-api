package dio.lab.santander.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dio.lab.santander.domain.News;

@JsonInclude( JsonInclude.Include.NON_EMPTY )
public record NewsDto( Long id,
                       String icon,
                       String description ) {

  public NewsDto( News model ) {
    this( model.id(), model.icon(), model.description() );
  }

  public News toModel() {
    return new News( this.id, this.icon, this.description );
  }

}

