package dio.lab.santander.repositories;

import dio.lab.santander.repositories.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  boolean existsByAccountNumber( String number );

  boolean existsByCardNumber( String number );

}
