package guru.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BeerController.class)
class BeerControllerTest
{

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void getBeerById() throws Exception
	{
		mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID())
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());
	}

	@Test
	void saveNewBeer() throws Exception
	{
		val beerDto = BeerDto.builder().beerName("New beer").upc(1L).beerType(BeerStyleEnum.PILSNER).price(new BigDecimal("125")).build();		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		mockMvc.perform(post("/api/v1/beer/")
			.contentType(MediaType.APPLICATION_JSON)
			.content(beerDtoJson))
			.andExpect(status().isCreated());
	}

	@Test
	void updateBeerById() throws Exception
	{
		BeerDto beerDto = BeerDto.builder().beerName("New beer").upc(1L).beerType(BeerStyleEnum.PILSNER).price(new BigDecimal("125")).build();		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID())
				.contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson))
			.andExpect(status().isNoContent());
	}
}