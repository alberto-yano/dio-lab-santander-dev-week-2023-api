package dio.lab.santander.repositories.entities;

import dio.lab.santander.domain.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
@Entity( name = "tb_user" )
public class UserEntity {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private Long                id;
  private String              name;
  @OneToOne( cascade = CascadeType.ALL )
  private AccountEntity       account;
  @OneToOne( cascade = CascadeType.ALL )
  private CardEntity          card;
  @ManyToMany( cascade = CascadeType.DETACH, fetch = FetchType.EAGER )
  private List<FeatureEntity> features;
  @ManyToMany( cascade = CascadeType.DETACH, fetch = FetchType.EAGER )
  private List<NewsEntity>    news;

  public UserEntity( User model ) {
    this( model, null, null );
  }

  public UserEntity( User model, List<FeatureEntity> features, List<NewsEntity> news ) {
    this.id = model.id();
    this.name = model.name();
    this.account = new AccountEntity( model.account() );
    this.card = new CardEntity( model.card() );
    this.features = features;
    this.news = news;
  }

  public User toModel() {
    return new User( this.id, this.name, ofNullable( this.account ).map( AccountEntity::toModel ).orElse( null ),
                     ofNullable( this.card ).map( CardEntity::toModel ).orElse( null ),
                     ofNullable( this.features ).orElse( emptyList() ).stream().map( FeatureEntity::toModel ).collect(
                         toList() ),
                     ofNullable( this.news ).orElse( emptyList() ).stream().map( NewsEntity::toModel ).collect(
                         toList() ) );
  }

}
