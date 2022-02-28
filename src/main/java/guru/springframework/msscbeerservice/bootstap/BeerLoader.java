package guru.springframework.msscbeerservice.bootstap;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.util.UUID;

//@Component
public class BeerLoader implements CommandLineRunner
{
	public static final String BEER_1_UPC = "0631234200036";
	public static final String BEER_2_UPC = "0631234300019";
	public static final String BEER_3_UPC = "00837833755213";
	public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
	public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
	public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

	private final BeerRepository beerRepository;

	public BeerLoader(BeerRepository beerRepository)
	{
		this.beerRepository = beerRepository;
	}

	@Override
	public void run(String... args) throws Exception
	{
		loadBeerObjects();
	}

	private void loadBeerObjects()
	{
		if (beerRepository.count() == 0)
		{
			beerRepository.save(Beer.builder()
					.beerName("Soproni Ászok")
					.beerStyle("Pilsner")
					.quantityToBrew(200)
					.minOnHand(20)
					.upc(BEER_1_UPC)
					.price(new BigDecimal("211"))
				.build());

			beerRepository.save(Beer.builder()
					.beerName("Dreher")
					.beerStyle("Pilsner")
					.quantityToBrew(200)
					.minOnHand(20)
					.upc(BEER_2_UPC)
					.price(new BigDecimal("220"))
				.build());

			beerRepository.save(Beer.builder()
					.beerName("Charlie Firpo")
					.beerStyle("IPA")
					.quantityToBrew(200)
					.minOnHand(20)
					.upc(BEER_3_UPC)
					.price(new BigDecimal("250"))
				.build());

			System.out.println("Default upload beer: " + beerRepository.count());
		}

		System.out.println("Existing beer: " + beerRepository.count());
	}
}
