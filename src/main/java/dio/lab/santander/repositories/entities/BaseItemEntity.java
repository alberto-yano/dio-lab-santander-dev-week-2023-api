package dio.lab.santander.repositories.entities;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseItemEntity {

  @Id
  private Long   id;
  private String icon;
  private String description;

}
