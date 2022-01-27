package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService
{
	BeerDto getById(UUID beerId);

	BeerDto saveNewBeer(BeerDto beerDto);

	BeerDto updateById(UUID beerId, BeerDto beerDto);

	boolean deleteById(UUID beerId);

}
