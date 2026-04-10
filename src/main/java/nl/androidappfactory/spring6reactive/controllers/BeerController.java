package nl.androidappfactory.spring6reactive.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.androidappfactory.spring6reactive.mappers.BeerMapper;
import nl.androidappfactory.spring6reactive.model.BeerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BeerController {

    public static final String BEER_PATH = "/api/v2/beer";
    public final BeerMapper mapper;

    @GetMapping(BEER_PATH)
    Flux<BeerDto> listBeers() {
        return Flux.just(
                BeerDto.builder().id(1).beerName("Bavaria").build(),
                BeerDto.builder().id(2).beerName("Hertog Jan").build());
    }
}
