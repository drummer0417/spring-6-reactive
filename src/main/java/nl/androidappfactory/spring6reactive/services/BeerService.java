package nl.androidappfactory.spring6reactive.services;

import nl.androidappfactory.spring6reactive.model.BeerDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerService {

    Flux<BeerDto> listBeers();

    Mono<BeerDto> getById(Integer id);

    Mono<BeerDto> saveBeer(BeerDto beerDto);

    Mono<BeerDto> updateBeer(Integer id, BeerDto beerDto);

    Mono<BeerDto> patchBeer(Integer id, BeerDto beerDto);

    Mono<Void> delete(Integer id);
}
