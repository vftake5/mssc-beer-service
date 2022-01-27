package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.services.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController
{

	private final BeerService beerService;

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId)
	{
		return new ResponseEntity<>(beerService.getById(beerId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto)
	{
		return new ResponseEntity<>(beerService.saveNewBeer(beerDto), HttpStatus.OK);
	}

	@PutMapping("/{beerId}")
	public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto)
	{
		return new ResponseEntity<>(beerService.updateById(beerId, beerDto), HttpStatus.NO_CONTENT);
 	}

	@DeleteMapping("/{beerId}")
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
