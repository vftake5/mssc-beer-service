package guru.springframework.msscbeerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto
{
	@NotNull
	private UUID id;
	private Integer version;

	private OffsetDateTime createdDate;
	private OffsetDateTime lastModifiedDate;

	@NotBlank
	private String beerName;

	private BeerStyleEnum beerType;

	@NotNull
	@Positive
	private Long upc;
	private BigDecimal price;

	private Integer quantityOnHand;
}
