package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.mapper.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import hu.vf.guru.msscbeercommon.events.BeerDto;
import hu.vf.guru.msscbeercommon.events.BeerStyleEnum;
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


	@Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false ")
	@Override
	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand)
	{
		BeerPagedList beerPagedList;
		Page<Beer> beerPage;
		String beerStyleName = beerStyle == null ? null : beerStyle.name();

//		System.out.println("  -------- listBeers called ------");

		if (StringUtils.hasLength(beerName) && StringUtils.hasLength(beerStyleName)) {
			//search both
			beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
		} else if (StringUtils.hasLength(beerName) && !StringUtils.hasLength(beerStyleName)) {
			//search beer_service name
			beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
		} else if (!StringUtils.hasLength(beerName) && StringUtils.hasLength(beerStyleName)) {
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
				.map(beerMapper::beerToBeerDto)
				.collect(Collectors.toList()),
				PageRequest
					.of(beerPage.getPageable().getPageNumber(),
						beerPage.getPageable().getPageSize()),
				beerPage.getTotalElements());
		}

		return beerPagedList;
	}

	@Cacheable(cacheNames = "beerCache", key="#beerId", condition = "#showInventoryOnHand == false ")
	@Override
	public BeerDto getById(UUID beerId, Boolean showInventoryOnHand)
	{
//		System.out.println("  -------- getById called ------");

		if (showInventoryOnHand)
		{
			return beerMapper.beerToBeerDtoWithInventory(
				beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
		}
		else
		{
			return beerMapper.beerToBeerDto(
				beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
		}
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
		beer.setBeerStyle(beerDto.getBeerStyle().name());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc());

		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}

	@Override
	public boolean deleteById(UUID beerId)
	{
		// TODO delete eset??n pontos??tani az elv??r??st. Kell-e elle??rizni a t??rl??st, sz??m??t-e, ha nem volt mit t??r??lni?
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

	@Cacheable(cacheNames = "beerUpcCache", key="#beerUpc", condition = "#showInventoryOnHand == false ")
	@Override
	public BeerDto getByUpc(String beerUpc, Boolean showInventoryOnHand)
	{
		if (showInventoryOnHand)
		{
			return beerMapper.beerToBeerDtoWithInventory(
				beerRepository.findByUpc(beerUpc));
		}
		else
		{
			return beerMapper.beerToBeerDto(
				beerRepository.findByUpc(beerUpc));
		}
	}
}
