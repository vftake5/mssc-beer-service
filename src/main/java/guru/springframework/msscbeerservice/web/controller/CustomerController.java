package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.web.model.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController
{
	@GetMapping("/{custId}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("custId") UUID custId)
	{
		return new ResponseEntity<>(CustomerDto.builder().build(), HttpStatus.OK);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<CustomerDto> getCustomerByName(@PathVariable("name") String name)
	{
		return new ResponseEntity<>(CustomerDto.builder().build(), HttpStatus.OK);
	}

	@PostMapping("/savecust")
	public ResponseEntity<UUID> saveNewCustomer(@RequestBody CustomerDto customerDto)
	{
		ResponseEntity responseEntity = new ResponseEntity<UUID>(customerDto.getId(), HttpStatus.CREATED);

		return responseEntity;
	}

	@PutMapping("/update/{custId}")
	public ResponseEntity<UUID> updateCustomerById(@PathVariable("custId") UUID custId, @RequestBody CustomerDto customerDto)
	{
		ResponseEntity responseEntity = new ResponseEntity<UUID>(customerDto.getId(), HttpStatus.NO_CONTENT);

		return responseEntity;

	}

	@DeleteMapping("/delete/{custId}")
	public ResponseEntity<UUID> delCustomerById(@PathVariable("custId")UUID custId)
	{
		return new ResponseEntity(custId, HttpStatus.NO_CONTENT);
	}

}
