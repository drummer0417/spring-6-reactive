package nl.androidappfactory.spring6reactive.repositories;

import nl.androidappfactory.spring6reactive.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonRepositoryImpl implements PersonRepository {

    Person hans = new Person(1, "Hans", "Jansen");
    Person jacky = Person.builder().firstName("Jacky").lastName("Pieterse").id(2).build();
    Person cas = Person.builder().firstName("Cas").lastName("Johnson").id(3).build();
    Person anouk = Person.builder().firstName("Anouk").lastName("Jackson").id(4).build();

    Map<Integer, Person> persons = Map.of(
            hans.getId(), hans,
            jacky.getId(), jacky,
            cas.getId(), cas,
            anouk.getId(), anouk
    );


    @Override
    public Mono<Person> getById(Integer id) {
        return Mono.justOrEmpty(persons.get(id));
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.just(persons.get(1), persons.get(2), persons.get(3), persons.get(4)
        );
    }
}
