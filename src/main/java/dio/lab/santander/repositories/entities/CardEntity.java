package dio.lab.santander.repositories.entities;

import dio.lab.santander.domain.Card;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity( name = "tb_card" )
public class CardEntity {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private Long       id;
  @Column( unique = true )
  private String     number;
  @Column( name = "available_limit", precision = 13, scale = 2 )
  private BigDecimal limit;

  public CardEntity( Card model ) {
    this.id = model.id();
    this.number = model.number();
    this.limit = model.limit();
  }

  public Card toModel() {
    return new Card( this.id, this.number, this.limit );
  }

}
