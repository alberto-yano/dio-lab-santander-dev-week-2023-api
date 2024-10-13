package dio.lab.santander.repositories;

import dio.lab.santander.repositories.entities.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

}
