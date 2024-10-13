package dio.lab.santander.domain;

import java.util.List;

public record User( Long id,
                    String name,
                    Account account,
                    Card card,
                    List<Feature> features,
                    List<News> news ) {

}

