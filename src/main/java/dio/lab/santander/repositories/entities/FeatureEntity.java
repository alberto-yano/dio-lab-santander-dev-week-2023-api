package dio.lab.santander.repositories.entities;

import dio.lab.santander.domain.Feature;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity( name = "tb_feature" )
public class FeatureEntity extends BaseItemEntity {

  public FeatureEntity( Feature model ) {
    setId( model.id() );
    setIcon( model.icon() );
    setDescription( model.description() );
  }

  public Feature toModel() {
    return new Feature( this.getId(), this.getIcon(), this.getDescription() );
  }

}
