package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.mapper.BeerInvMapper;
import guru.springframework.msscbeerservice.web.mapper.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService
{
	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;
	private final BeerInvMapper beerInvMapper;


	@Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false ")
	@Override
	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {

		BeerPagedList beerPagedList;
		Page<Beer> beerPage;

		if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			//search both
			beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
		} else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
			//search beer_service name
			beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
		} else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			//search beer_service style
			beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
		} else {
			beerPage = beerRepository.findAll(pageRequest);
		}

		if (showInventoryOnHand){
			beerPagedList = new BeerPagedList(beerPage
				.getContent()
				.stream()
				.map(beerMapper::beerToBeerDtoWithInventory)
				.collect(Collectors.toList()),
				PageRequest
					.of(beerPage.getPageable().getPageNumber(),
						beerPage.getPageable().getPageSize()),
				beerPage.getTotalElements());
		} else {
			beerPagedList = new BeerPagedList(beerPage
				.getContent()
				.stream()
//				.map(beerMapper::beerToBeerDto)
				.map(beerInvMapper::beerToBeerDtoWI)
				.collect(Collectors.toList()),
				PageRequest
					.of(beerPage.getPageable().getPageNumber(),
						beerPage.getPageable().getPageSize()),
				beerPage.getTotalElements());
		}

		return beerPagedList;
	}

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
		beer.setBeerStyle(beerDto.getBeerType().name());
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
