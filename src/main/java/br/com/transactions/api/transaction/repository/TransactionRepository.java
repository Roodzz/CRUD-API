package br.com.transactions.api.transaction.repository;

import br.com.transactions.api.customer.entity.Customer;
import br.com.transactions.api.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository <Transaction, UUID> {

    List<Transaction> findAllByCompanyId(UUID companyId);
}
