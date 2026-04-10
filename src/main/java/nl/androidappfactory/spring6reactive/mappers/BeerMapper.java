package nl.androidappfactory.spring6reactive.mappers;


import ch.qos.logback.core.model.ComponentModel;
import nl.androidappfactory.spring6reactive.domain.Beer;
import nl.androidappfactory.spring6reactive.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeerMapper {

    BeerDto toBeerDto (Beer beer);
    Beer toBeer (BeerDto beerDto);
}
