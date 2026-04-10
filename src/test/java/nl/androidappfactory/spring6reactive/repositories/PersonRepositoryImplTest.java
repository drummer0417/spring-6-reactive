package nl.androidappfactory.spring6reactive.repositories;

import nl.androidappfactory.spring6reactive.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;import static org.junit.jupiter.api.Assertions.assertEquals;import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

    @Test
    void findByNonExistingId() {

        Integer id = 31;
        Flux<Person> personFlux = personRepository.findAll();

        Mono<Person> personMono = personFlux
                .filter(person -> person.getId().equals(id))
                .single()
                .doOnError(throwable -> {
                    System.out.println(throwable.getMessage());
                    System.out.println("Error on flux");
                });

        personMono.subscribe(System.out::println, throwable -> System.out.println("Error on mono"));
    }

    @Test
    void findByNonExistingId2() {

        final Integer id = 31;
        Flux<Person> personFlux = personRepository.findAll();

        Mono<Person> personMono = personFlux
                .filter(person -> person.getId().equals(id))
                .next();
        assertNotEquals(Boolean.TRUE, personMono.hasElement().block());
    }

    @Test
    void findByExistingId() {

        final Integer id = 3;
        Flux<Person> personFlux = personRepository.findAll();

        Mono<Person> personMono = personFlux
                .filter(person -> person.getId().equals(id))
                .next();
        assertEquals(Boolean.TRUE, personMono.hasElement().block());
    }
}
