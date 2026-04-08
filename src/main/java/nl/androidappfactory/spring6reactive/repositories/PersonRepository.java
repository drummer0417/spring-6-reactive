package nl.androidappfactory.spring6reactive.repositories;

import nl.androidappfactory.spring6reactive.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository {

    Mono<Person> getById(Integer id);

    Flux<Person> findAll();
}
