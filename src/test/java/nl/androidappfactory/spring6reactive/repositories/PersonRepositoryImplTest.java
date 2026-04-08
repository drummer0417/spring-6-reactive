package nl.androidappfactory.spring6reactive.repositories;

import nl.androidappfactory.spring6reactive.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

class PersonRepositoryImplTest {

    private final PersonRepositoryImpl personRepository = new PersonRepositoryImpl();

    @Test
    void testMonoByIdBlock() {
        Mono<Person> personMono = personRepository.getById(1);
        Person person = personMono.block();

        System.out.println(person);
    }

    @Test
    void testMonoByIdSubscribe() {
        Mono<Person> personMono = personRepository.getById(3);

        personMono.subscribe(System.out::println);
    }

    @Test
    void testMonoMapPerson() {
        Mono<Person> personMono = personRepository.getById(2);

        personMono.map(Person::getFirstName).subscribe(System.out::println);
    }

    @Test
    void testFluxBlock() {
        Flux<Person> personFlux = personRepository.findAll();

        Person person = personFlux.blockFirst();
        System.out.println(person);
    }

    @Test
    void testFluxSubscribe() {
        Flux<Person> personFlux = personRepository.findAll();

        personFlux.subscribe(System.out::println);
    }

    @Test
    void testFluxMap() {
        Flux<Person> personFlux = personRepository.findAll();

        personFlux.map(Person::getFirstName)
                .subscribe(System.out::println);
    }

    @Test
    void testFluxList() {
        Flux<Person> personFlux = personRepository.findAll();

        Mono<List<Person>> listMono = personFlux.collectList();
        listMono.subscribe(list -> list.forEach(System.out::println));


    }

    @Test
    void testFluxFilter() {
        personRepository.findAll()
                .filter(person -> person.getFirstName().equals("Hans"))
                .subscribe(System.out::println);
    }

    @Test
        // variant op vorige test met Flux
    void testMonoFilter() {

        Mono<Person> personMono = personRepository.findAll()
                .filter(person -> person.getFirstName().equals("Hans")).next();

        personMono.subscribe(System.out::println);
    }
}
