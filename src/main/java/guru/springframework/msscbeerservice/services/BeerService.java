package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import hu.vf.guru.msscbeercommon.events.BeerDto;
import hu.vf.guru.msscbeercommon.events.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService
{

	BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

	BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

	BeerDto saveNewBeer(BeerDto beerDto);

	BeerDto updateById(UUID beerId, BeerDto beerDto);

	boolean deleteById(UUID beerId);

	BeerDto getByUpc(String beerUpc, Boolean showInventoryOnHand);


}
