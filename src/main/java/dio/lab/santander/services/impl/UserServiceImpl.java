package dio.lab.santander.services.impl;

import dio.lab.santander.domain.User;
import dio.lab.santander.exceptions.BusinessException;
import dio.lab.santander.exceptions.NotFoundException;
import dio.lab.santander.repositories.FeatureRepository;
import dio.lab.santander.repositories.NewsRepository;
import dio.lab.santander.repositories.UserRepository;
import dio.lab.santander.repositories.entities.AccountEntity;
import dio.lab.santander.repositories.entities.CardEntity;
import dio.lab.santander.repositories.entities.FeatureEntity;
import dio.lab.santander.repositories.entities.NewsEntity;
import dio.lab.santander.repositories.entities.UserEntity;
import dio.lab.santander.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  /**
   * ID de usuário utilizado na Santander Dev Week 2023.
   * Por isso, vamos criar algumas regras para mantê-lo integro.
   */
  private static final Long UNCHANGEABLE_USER_ID = 1L;

  private final UserRepository    userRepository;
  private final FeatureRepository featureRepository;
  private final NewsRepository    newsRepository;

  @Transactional( value = Transactional.TxType.NEVER )
  public List<User> findAll() {
    return this.userRepository.findAll().stream().map( UserEntity::toModel ).toList();
  }

  @Transactional( value = Transactional.TxType.NEVER )
  public User findById( Long id ) {
    return this.userRepository.findById( id ).orElseThrow( NotFoundException::new ).toModel();
  }

  @Transactional
  public User create( User userToCreate ) {
    ofNullable( userToCreate ).orElseThrow( () -> new BusinessException( "User to create must not be null." ) );
    ofNullable( userToCreate.account() ).orElseThrow( () -> new BusinessException( "User account must not be null." ) );
    ofNullable( userToCreate.card() ).orElseThrow( () -> new BusinessException( "User card must not be null." ) );

    this.validateChangeableId( userToCreate.id(), "created" );
    if( userRepository.existsByAccountNumber( userToCreate.account().number() ) ) {
      throw new BusinessException( "This account number already exists." );
    }
    if( userRepository.existsByCardNumber( userToCreate.card().number() ) ) {
      throw new BusinessException( "This card number already exists." );
    }
    userToCreate.features().forEach( feature -> this.featureRepository.findById( feature.id() ).orElseGet(
        () -> this.featureRepository.save( new FeatureEntity( feature ) ) ) );
    userToCreate.news().forEach( n -> this.newsRepository.findById( n.id() ).orElseGet(
        () -> this.newsRepository.save( new NewsEntity( n ) ) ) );
    return this.userRepository.save( new UserEntity( userToCreate, userToCreate.features().stream().map(
        feature -> this.featureRepository.getReferenceById( feature.id() ) ).toList(), userToCreate.news().stream().map(
        n -> this.newsRepository.getReferenceById( n.id() ) ).toList() ) ).toModel();
  }

  @Transactional
  public User update( Long id, User userToUpdate ) {
    this.validateChangeableId( id, "updated" );
    User user = this.findById( id );
    if( !user.id().equals( userToUpdate.id() ) ) {
      throw new BusinessException( "Update IDs must be the same." );
    }
    UserEntity dbUser = this.userRepository.getReferenceById( userToUpdate.id() );
    dbUser.setName( userToUpdate.name() );
    dbUser.setAccount( new AccountEntity( userToUpdate.account() ) );
    dbUser.setCard( new CardEntity( userToUpdate.card() ) );
    dbUser.setFeatures( userToUpdate.features().stream().map( FeatureEntity::new ).toList() );
    dbUser.setNews( userToUpdate.news().stream().map( NewsEntity::new ).toList() );

    return this.userRepository.save( dbUser ).toModel();
  }

  @Transactional
  public void delete( Long id ) {
    this.validateChangeableId( id, "deleted" );
    User dbUser = this.findById( id );
    this.userRepository.delete( new UserEntity( dbUser ) );
  }

  private void validateChangeableId( Long id, String operation ) {
    if( UNCHANGEABLE_USER_ID.equals( id ) ) {
      throw new BusinessException( "User with ID %d can not be %s.".formatted( UNCHANGEABLE_USER_ID, operation ) );
    }
  }

}

