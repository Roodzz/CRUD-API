package br.com.transactions.api;


import br.com.transactions.api.customer.entity.Customer;
import br.com.transactions.api.customer.repository.CustomerRepository;
import br.com.transactions.api.customer.service.CustomerService;
import br.com.transactions.api.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceTests {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;


    @Test
    void shouldCreateCustomerWithSuccess() {
        var customer = Mockito.mock(Customer.class);

        when(customerRepository.save(any())).thenReturn(customer);

        var customerCreated = customerService.save(customer);

        verify(customerRepository, atLeastOnce()).save(any());
        assertEquals(customerCreated.getId(), customer.getId());
        assertEquals(customerCreated.getName(), customer.getName());
    }

    @Test
    void shouldFindCustomerWithSuccess() {
        var customer = Mockito.mock(Customer.class);

        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));

        var existentCustomer = customerService.findById(UUID.randomUUID().toString());

        verify(customerRepository, atLeastOnce()).findById(any());
        assertEquals(existentCustomer.getId(), customer.getId());
        assertEquals(existentCustomer.getName(), customer.getName());
    }

    @Test
    void shouldFindCustomerIfNotExistsThrowError() {
        when(customerRepository.findById(any())).thenThrow(new NotFoundException("Não encontrado"));

        assertThrows(NotFoundException.class, () -> customerService.findById(UUID.randomUUID().toString()));
    }


}
