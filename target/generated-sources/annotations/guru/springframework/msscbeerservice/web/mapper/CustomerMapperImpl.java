package guru.springframework.msscbeerservice.web.mapper;

import guru.springframework.msscbeerservice.domain.Customer;
import guru.springframework.msscbeerservice.domain.Customer.CustomerBuilder;
import guru.springframework.msscbeerservice.web.model.CustomerDto;
import guru.springframework.msscbeerservice.web.model.CustomerDto.CustomerDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-02T20:31:14+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDtoBuilder customerDto = CustomerDto.builder();

        customerDto.id( customer.getId() );
        customerDto.name( customer.getName() );
        customerDto.nationality( customer.getNationality() );

        return customerDto.build();
    }

    @Override
    public Customer customerDtoToCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        CustomerBuilder customer = Customer.builder();

        customer.id( customerDto.getId() );
        customer.name( customerDto.getName() );
        customer.nationality( customerDto.getNationality() );

        return customer.build();
    }
}
