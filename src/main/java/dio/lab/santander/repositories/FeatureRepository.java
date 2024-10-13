package dio.lab.santander.repositories;

import dio.lab.santander.repositories.entities.FeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<FeatureEntity, Long> {

}
