package nl.androidappfactory.spring6reactive.repositories;

import nl.androidappfactory.spring6reactive.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {
}
