package dio.lab.santander.domain;

import java.math.BigDecimal;

public record Account( Long id,
                       String number,
                       String agency,
                       BigDecimal balance,
                       BigDecimal limit ) {

}
