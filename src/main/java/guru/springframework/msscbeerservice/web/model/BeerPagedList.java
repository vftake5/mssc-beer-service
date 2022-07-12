package guru.springframework.msscbeerservice.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import hu.vf.guru.msscbeercommon.events.BeerDto;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class BeerPagedList extends PageImpl<BeerDto> implements Serializable
{
	static final long serialVersionUID = 5894495156196945533L;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public BeerPagedList(@JsonProperty("content") List<BeerDto> content,
		@JsonProperty("number") int number,
		@JsonProperty("size") int size,
		@JsonProperty("totalElements") Long totalElements,
		@JsonProperty("pageable") JsonNode pageable,
		@JsonProperty("totalPages") int totalPages,
		@JsonProperty("last") boolean last,
		@JsonProperty("sort") JsonNode sort,
		@JsonProperty("first") boolean first,
		@JsonProperty("numberOfElements") int numberOfElements

		)

	{
		super(content, PageRequest.of(number, size), totalElements);
	}

	public BeerPagedList(List<BeerDto> content, Pageable pageable, long total)
	{
		super(content, pageable, total);
	}

	public BeerPagedList(List<BeerDto> content)
	{
		super(content);
	}
}
