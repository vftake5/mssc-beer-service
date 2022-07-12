package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.services.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import hu.vf.guru.msscbeercommon.events.BeerDto;
import hu.vf.guru.msscbeercommon.events.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Validálandó mezők beállítását lásd: BeerDto
 * A Validáció kapcsán került bele egy "@ExceptionHandler" annotációjú eljárás.
 * A class definiálja a kezelt kivételt.
 * A hiba objektumba bekerül a hiba mező szinten meghatározott oka.
 *
 * Client oldalon a kezelését lásd guru.springframework.msscbreweryclient.web.client.BreweryClient.saveNewBeerW(BeerDto beerDto)
 */

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/")
@RestController
public class BeerController
{
	private static final Integer DEFAULT_PAGE_NUMBER = 0;
	private static final Integer DEFAULT_PAGE_SIZE = 25;

	private final BeerService beerService;

	@GetMapping(produces = { "application/json" }, path = "beer")
	public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
		@RequestParam(value = "pageSize", required = false) Integer pageSize,
		@RequestParam(value = "beerName", required = false) String beerName,
		@RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
		@RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand)
	{

		if (showInventoryOnHand == null)
		{
			showInventoryOnHand = false;
		}

		if (pageNumber == null || pageNumber < 0)
		{
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		if (pageSize == null || pageSize < 0)
		{
			pageSize = DEFAULT_PAGE_SIZE;
		}

		BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize),  showInventoryOnHand);

		return new ResponseEntity<>(beerList, HttpStatus.OK);
	}

	@GetMapping("beer/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId,
		@RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand)
	{
		if (showInventoryOnHand == null)
		{
			showInventoryOnHand = false;
		}

		return new ResponseEntity<>(beerService.getById(beerId, showInventoryOnHand), HttpStatus.OK);
	}

	@GetMapping("beerUpc/{upc}")
	public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable("upc") String upc,
		@RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand)
	{
		if (showInventoryOnHand == null)
		{
			showInventoryOnHand = false;
		}

		BeerDto responseBeerDto = beerService.getByUpc(upc, showInventoryOnHand);

		if (responseBeerDto != null)

			return new ResponseEntity<>(responseBeerDto, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}


	@PostMapping(path = "beer")
	public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto)
	{
		return new ResponseEntity<>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
	}

	@PutMapping("beer/{beerId}")
	public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto)
	{
		return new ResponseEntity<>(beerService.updateById(beerId, beerDto), HttpStatus.NO_CONTENT);
 	}

	@DeleteMapping("beer/{beerId}")
	public ResponseEntity deleteBeerById(@PathVariable("beerId") UUID beerId)
	{
		// TODO megcsinálni
		return new ResponseEntity<>(beerService.deleteById(beerId), HttpStatus.NO_CONTENT);
//		return ;
	}

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<List> validationErrorHandler(MethodArgumentNotValidException e)
//	{
//		List<ObjectError> errorList = e.getAllErrors();
//		List<String> error = new ArrayList<>(errorList.size());
//
//		e.getAllErrors().forEach(argNotValid -> {
//			error.add(argNotValid.toString() + " | " + argNotValid.getCode() + " : " + argNotValid.getDefaultMessage());
//		});
//
//		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//	}
}
