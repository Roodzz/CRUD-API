package br.com.transactions.api.customer.repository;

import br.com.transactions.api.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository <Customer, UUID> {

}
