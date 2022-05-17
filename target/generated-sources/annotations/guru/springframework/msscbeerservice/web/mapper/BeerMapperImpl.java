package guru.springframework.msscbeerservice.web.mapper;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-17T14:07:36+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
@Component
@Primary
public class BeerMapperImpl extends BeerMapperDecorator implements BeerMapper {

    @Autowired
    @Qualifier("delegate")
    private BeerMapper delegate;

    @Override
    public BeerDto beerToBeerDto(Beer beer)  {
        return delegate.beerToBeerDto( beer );
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto)  {
        return delegate.beerDtoToBeer( beerDto );
    }
}
