package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Validálandó mezők beállítását lásd: BeerDto
 * A Validáció kapcsán került bele egy "@ExceptionHandler" annotációjú eljárás.
 * A class definiálja a kezelt kivételt.
 * A hiba objektumba bekerül a hiba mező szinten meghatározott oka.
 *
 * Client oldalon a kezelését lásd guru.springframework.msscbreweryclient.web.client.BreweryClient.saveNewBeerW(BeerDto beerDto)
 */

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController
{

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId)
	{
		// TODO megcsinálni
		return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UUID> saveNewBeer(@Valid @RequestBody BeerDto beerDto)
	{
		// TODO megcsinálni
		System.out.println("BeerDTO: " + beerDto);

		ResponseEntity responseEntity = new ResponseEntity<UUID>(beerDto.getId(), HttpStatus.CREATED);

		System.out.println("ResponseEntity: " + responseEntity.toString());

 		return responseEntity;
	}

	@PutMapping("/{beerId}")
	public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto)
	{
		// TODO megcsinálni
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{beerId}")
	public ResponseEntity deleteBeerById(@PathVariable("beerId") UUID beerId)
	{
		// TODO megcsinálni
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List> validationErrorHandler(MethodArgumentNotValidException e)
	{
		List<ObjectError> errorList = e.getAllErrors();
		List<String> error = new ArrayList<>(errorList.size());

		e.getAllErrors().forEach(argNotValid -> {
			error.add(argNotValid.toString() + " | " + argNotValid.getCode() + " : " + argNotValid.getDefaultMessage());
		});

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
