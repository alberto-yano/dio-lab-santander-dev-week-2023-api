package dio.lab.santander.repositories.entities;

import dio.lab.santander.domain.Account;
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
@Entity( name = "tb_account" )
public class AccountEntity {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private Long       id;
  @Column( unique = true )
  private String     number;
  private String     agency;
  @Column( precision = 13, scale = 2 )
  private BigDecimal balance;
  @Column( name = "additional_limit", precision = 13, scale = 2 )
  private BigDecimal limit;

  public AccountEntity( Account model ) {
    this.id = model.id();
    this.number = model.number();
    this.agency = model.agency();
    this.balance = model.balance();
    this.limit = model.limit();
  }

  public Account toModel() {
    return new Account( this.id, this.number, this.agency, this.balance, this.limit );
  }

}
