package nl.androidappfactory.spring6reactive.repositories;

import nl.androidappfactory.spring6reactive.config.DatabaseConfig;
import nl.androidappfactory.spring6reactive.domain.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.r2dbc.test.autoconfigure.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataR2dbcTest
@Import(DatabaseConfig.class)
class BeerRepositoryImplTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void saveNewBeer() {
        beerRepository.save(getCreateBeer())
                .subscribe(System.out::println);
    }

    Beer getCreateBeer() {
        return Beer.builder()
//                .id(Integer.valueOf(UUID.randomUUID()))
                .beerName("Bavaria")
                .beerStyle("Pils")
                .price(new BigDecimal("2.39"))
                .upc("234295653")
                .build();
    }
}

