package dio.lab.santander.repositories.entities;

import dio.lab.santander.domain.News;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity( name = "tb_news" )
public class NewsEntity extends BaseItemEntity {

  public NewsEntity( News model ) {
    setId( model.id() );
    setIcon( model.icon() );
    setDescription( model.description() );
  }

  public News toModel() {
    return new News( this.getId(), this.getIcon(), this.getDescription() );
  }

}
