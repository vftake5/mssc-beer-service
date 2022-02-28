package guru.springframework.msscbeerservice.web.mapper;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
//@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper
{
	BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

	BeerDto beerToBeerDto(Beer beer);

	Beer beerDtoToBeer(BeerDto beerDto);

//	BeerDto beerToBeerDtoWithInventory(Beer beer);

}
