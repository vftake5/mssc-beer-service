package guru.springframework.msscbeerservice.web.mapper;

import guru.springframework.msscbeerservice.domain.Customer;
import guru.springframework.msscbeerservice.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(uses=DateMapper.class)
public interface CustomerMapper
{
	CustomerDto customerToCustomerDto(Customer customer);

	Customer customerDtoToCustomer(CustomerDto customerDto);
}
