package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
	public ResponseEntity<UUID> saveNewBeer(@RequestBody BeerDto beerDto)
	{
		// TODO megcsinálni
		System.out.println("BeerDTO: " + beerDto);

		ResponseEntity responseEntity = new ResponseEntity<UUID>(beerDto.getId(), HttpStatus.CREATED);

		System.out.println("ResponseEntity: " + responseEntity.toString());

 		return responseEntity;
	}

	@PutMapping("/{beerId}")
	public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto)
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


}
