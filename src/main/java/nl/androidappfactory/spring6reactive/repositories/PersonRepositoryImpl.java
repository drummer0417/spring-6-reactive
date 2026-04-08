package nl.androidappfactory.spring6reactive.repositories;

import nl.androidappfactory.spring6reactive.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {

    Person nobudy = Person.builder().firstName("dummy").lastName("Nobody").id(0).build();
    Person hans = Person.builder().firstName("Hans").lastName("Jansen").id(1).build();
    Person jacky = Person.builder().firstName("Jacky").lastName("Pieterse").id(2).build();
    Person cas = Person.builder().firstName("Cas").lastName("Johnson").id(3).build();
    Person anouk = Person.builder().firstName("Anouk").lastName("Jackson").id(4).build();
    List<Person> persons = Arrays.asList(nobudy, hans, jacky, cas, anouk);

    @Override
    public Mono<Person> getById(Integer id) {
        return Mono.just(persons.get(id));
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.just(persons.get(1), persons.get(2), persons.get(3), persons.get(4)
        );
    }
}
