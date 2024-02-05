package br.com.transactions.api.customer.service;

import br.com.transactions.api.customer.entity.Customer;
import br.com.transactions.api.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        log.info("Saving new customer with name: {}", customer.getName());

        return customerRepository.save(customer);
    }

    public List<Customer> findAll(){
        log.info("Finding all customers");

        return customerRepository.findAll();
    }
    public Customer findById(String id){
        log.info("Finding customer with id: {}", id);

        return customerRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

}
