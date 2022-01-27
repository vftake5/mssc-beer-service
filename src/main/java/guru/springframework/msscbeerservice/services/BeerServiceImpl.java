package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.mapper.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService
{
	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;

	@Override
	public BeerDto getById(UUID beerId)
	{
		return beerMapper.beerToBeerDto(
			beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto)
	{
		return  beerMapper.beerToBeerDto(
			beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
	}

	@Override
	public BeerDto updateById(UUID beerId, BeerDto beerDto)
	{
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerType(beerDto.getBeerType().name());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc());

		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}

	@Override
	public boolean deleteById(UUID beerId)
	{
		// TODO delete esetén pontosítani az elvárást. Kell-e elleőrizni a törlést, számít-e, ha nem volt mit törölni?
		try
		{
			beerRepository.deleteById(beerId);

			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
