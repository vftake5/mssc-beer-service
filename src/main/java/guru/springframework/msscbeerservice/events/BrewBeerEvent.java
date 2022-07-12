package guru.springframework.msscbeerservice.events;

import hu.vf.guru.msscbeercommon.events.BeerDto;
import hu.vf.guru.msscbeercommon.events.BeerEvent;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent
{
	public BrewBeerEvent(BeerDto beerDto)
	{
		super(beerDto);
	}
}
