package nl.androidappfactory.spring6reactive.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.androidappfactory.spring6reactive.model.BeerDto;
import nl.androidappfactory.spring6reactive.services.BeerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BeerController {

    public static final String BEER_PATH = "/api/v2/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{id}";

    public final BeerService beerService;

    @GetMapping(BEER_PATH)
    Flux<BeerDto> listBeers() {
        return beerService.listBeers();
    }

    @GetMapping(BEER_PATH_ID)
    Mono<BeerDto> getById(@PathVariable("id") Integer id) {
        return beerService.getById(id);
    }

    @PostMapping(BEER_PATH)
    Mono<ResponseEntity<Void>> saveBeer(@RequestBody BeerDto beerDto) {
        return beerService.saveBeer(beerDto)
                .map(savedDto -> ResponseEntity
                        .created(UriComponentsBuilder
                                .fromUriString("http://localhost:8080" + BEER_PATH + "/" + savedDto.getId())
                                .build().toUri()).build());
    }

    @PutMapping(BEER_PATH_ID)
    Mono<ResponseEntity<Void>> updateBeer(@PathVariable Integer id, @RequestBody BeerDto beerUpdate) {
        return beerService.updateBeer(id, beerUpdate)
                .map(updateBeer -> ResponseEntity.noContent().build());
    }

    @PatchMapping(BEER_PATH_ID)
    Mono<ResponseEntity<Void>> patchBeer(@PathVariable Integer id, @RequestBody BeerDto beerPatch) {
        return beerService.patchBeer(id, beerPatch)
                .map(patchedBeer -> ResponseEntity.noContent().build());
    }

    @DeleteMapping(BEER_PATH_ID)
    Mono<ResponseEntity<Void>> delete(@PathVariable Integer id) {
        return beerService.delete(id).thenReturn(ResponseEntity.noContent().build());
    }
}