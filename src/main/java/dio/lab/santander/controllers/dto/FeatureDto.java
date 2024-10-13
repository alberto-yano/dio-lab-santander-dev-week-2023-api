package dio.lab.santander.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dio.lab.santander.domain.Feature;

@JsonInclude( JsonInclude.Include.NON_EMPTY )
public record FeatureDto( Long id,
                          String icon,
                          String description ) {

  public FeatureDto( Feature model ) {
    this( model.id(), model.icon(), model.description() );
  }

  public Feature toModel() {
    return new Feature( this.id, this.icon, this.description );
  }

}

