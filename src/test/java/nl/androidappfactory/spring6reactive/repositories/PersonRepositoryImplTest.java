package nl.androidappfactory.spring6reactive.repositories;

import nl.androidappfactory.spring6reactive.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

class PersonRepositoryImplTest {

    private final PersonRepositoryImpl personRepository = new PersonRepositoryImpl();

    @Test
    void testMonoByIdBlock() {
        Mono<Person> personMono = personRepository.getById(1);
        Person person = personMono.block();

        System.out.println(person);
    }

    @Test
    void testMonoByIdSubscribe(){
        Mono<Person> personMono = personRepository.getById(3);

        personMono.subscribe(System.out::println);
    }
}