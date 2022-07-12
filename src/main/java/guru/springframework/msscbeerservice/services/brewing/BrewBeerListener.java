package guru.springframework.msscbeerservice.services.brewing;

import guru.springframework.msscbeerservice.config.JmsConfig;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.events.BrewBeerEvent;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import hu.vf.guru.msscbeercommon.events.BeerDto;
import hu.vf.guru.msscbeercommon.events.NewInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener
{
	private final BeerRepository repository;
	private final JmsTemplate jmsTemplate;

	@Transactional
	@JmsListener(destination = JmsConfig.BREWING_REQ_Q)
	public void listener(BrewBeerEvent event)
	{
		BeerDto beerDto = event.getBeerDto();

		Beer beer = repository.getReferenceById(beerDto.getId());

		beerDto.setQuantityOnHand(beer.getQuantityToBrew());

		NewInventoryEvent inventoryEvent = new NewInventoryEvent(beerDto);

		log.debug("Brewed beer " + beer.getBeerName() +  " : QUH: " + beer.getQuantityToBrew());

		jmsTemplate.convertAndSend(JmsConfig.INVENTORY_REQ_Q, inventoryEvent);
	}
}
