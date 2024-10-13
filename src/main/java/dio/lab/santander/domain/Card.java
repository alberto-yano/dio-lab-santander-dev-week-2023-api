package dio.lab.santander.domain;

import java.math.BigDecimal;

public record Card( Long id,
                    String number,
                    BigDecimal limit ) {

}
