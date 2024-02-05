package br.com.transactions.api;


import br.com.transactions.api.client.WebWrapperClient;
import br.com.transactions.api.company.entity.Company;
import br.com.transactions.api.company.service.CompanyService;
import br.com.transactions.api.customer.entity.Customer;
import br.com.transactions.api.customer.service.CustomerService;
import br.com.transactions.api.transaction.dto.request.TransactionCreateDTO;
import br.com.transactions.api.transaction.entity.Transaction;
import br.com.transactions.api.transaction.entity.TransactionType;
import br.com.transactions.api.transaction.repository.TransactionRepository;
import br.com.transactions.api.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransactionServiceTests {

    @Mock
    private TransactionRepository transactionRepository;


    @Mock
    private CustomerService customerService;


    @Mock
    private CompanyService companyService;


    @Mock
    private WebWrapperClient webWrapperClient;

    @Spy
    @InjectMocks
    private TransactionService transactionService;


    @Test
    void shouldCreateTransactionWithSuccess() {
        var transaction = TransactionCreateDTO.builder()
                .type(TransactionType.DEPOSIT)
                .amount(BigDecimal.valueOf(100))
                .companyId(UUID.randomUUID().toString())
                .customerId(UUID.randomUUID().toString())
                .build();

        var customer = Mockito.mock(Customer.class);

        var company = Company.builder()
                .transactionTax(BigDecimal.valueOf(5))
                .build();

        when(customerService.findById(any())).thenReturn(customer);
        when(companyService.findById(any())).thenReturn(company);

        doNothing().when(transactionService).notifyWebhook(any());

        when(transactionRepository.save(any())).thenReturn(Transaction.builder().id(UUID.randomUUID()).build());

        transactionService.save(transaction);

        verify(transactionRepository, atLeastOnce()).save(any());
    }

    @Test
    void shouldRetrieveConsolidatedTransactionWithSuccess() {
        var transactionsMock = List.of(
                Transaction.builder()
                        .type(TransactionType.DEPOSIT)
                        .id(UUID.randomUUID())
                        .customer(Customer.builder().id(UUID.randomUUID()).build())
                        .company(Company.builder().id(UUID.randomUUID()).build())
                        .amount(BigDecimal.valueOf(10))
                        .build()
        );
        when(transactionRepository.findAllByCompanyId(any())).thenReturn(transactionsMock);

        transactionService.findConsolidatedByCompanyId(UUID.randomUUID().toString());

        verify(transactionRepository, atLeastOnce()).findAllByCompanyId(any());
    }


}
