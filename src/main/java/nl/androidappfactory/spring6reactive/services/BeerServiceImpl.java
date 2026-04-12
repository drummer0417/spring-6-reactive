package nl.androidappfactory.spring6reactive.services;

import lombok.RequiredArgsConstructor;
import nl.androidappfactory.spring6reactive.mappers.BeerMapper;
import nl.androidappfactory.spring6reactive.model.BeerDto;
import nl.androidappfactory.spring6reactive.repositories.BeerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Flux<BeerDto> listBeers() {
        return beerRepository.findAll()
                .map(beerMapper::toBeerDto);
    }

    @Override
    public Mono<BeerDto> getById(Integer id) {
        return beerRepository.findById(id).map(beerMapper::toBeerDto);
    }

    @Override
    public Mono<BeerDto> saveBeer(BeerDto beerDto) {
        return beerRepository.save(beerMapper.toBeer(beerDto)).map(beerMapper::toBeerDto);
    }

    @Override
    public Mono<BeerDto> updateBeer(Integer id, BeerDto beerUpdate) {
        return beerRepository.findById(id)
                .map(foundBeer -> {
                    foundBeer.setBeerName(beerUpdate.getBeerName());
                    foundBeer.setBeerStyle(beerUpdate.getBeerStyle());
                    foundBeer.setUpc(beerUpdate.getUpc());
                    foundBeer.setQuantityOnHand(beerUpdate.getQuantityOnHand());
                    foundBeer.setPrice(beerUpdate.getPrice());
                    return foundBeer;
                }).flatMap(beerRepository::save)
                .map(beerMapper::toBeerDto);
    }

    @Override
    public Mono<BeerDto> patchBeer(Integer id, BeerDto beerPatch) {
        return beerRepository.findById(id)
                .map(foundBeer -> {
                    foundBeer.setPrice(beerPatch.getPrice());
                    if (beerPatch.getBeerName() != null) foundBeer.setBeerName(beerPatch.getBeerName());
                    if (beerPatch.getBeerStyle() != null) foundBeer.setBeerStyle(beerPatch.getBeerStyle());
                    if (beerPatch.getUpc() != null) foundBeer.setUpc(beerPatch.getUpc());
                    if (beerPatch.getQuantityOnHand() != null)
                        foundBeer.setQuantityOnHand(beerPatch.getQuantityOnHand());
                    if (beerPatch.getPrice() != null) foundBeer.setPrice(beerPatch.getPrice());
                    return foundBeer;
                }).flatMap(beerRepository::save)
                .map(beerMapper::toBeerDto);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return beerRepository.findById(id).flatMap(beerRepository::delete);
    }
}
