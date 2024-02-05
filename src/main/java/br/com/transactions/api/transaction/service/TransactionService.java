package br.com.transactions.api.transaction.service;

import br.com.transactions.api.client.WebWrapperClient;
import br.com.transactions.api.company.service.CompanyService;
import br.com.transactions.api.customer.service.CustomerService;
import br.com.transactions.api.transaction.dto.request.TransactionCreateDTO;
import br.com.transactions.api.transaction.dto.response.ConsolidatedTransactionResponseDTO;
import br.com.transactions.api.transaction.dto.response.TransactionResponseDTO;
import br.com.transactions.api.transaction.entity.Transaction;
import br.com.transactions.api.transaction.entity.TransactionType;
import br.com.transactions.api.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;
    private final CompanyService companyService;
    private final WebWrapperClient webWrapperClient;

    @Value("${webhook.url}")
    private String webhookURL;

    public Transaction save(TransactionCreateDTO createDTO) {
        log.info("Adding new transaction for companyId: {} by customerId: {}", createDTO.getCompanyId(), createDTO.getCustomerId());

        var company = companyService.findById(createDTO.getCompanyId());
        var customer = customerService.findById(createDTO.getCustomerId());

        var transaction = Transaction.builder()
                .company(company)
                .type(createDTO.getType())
                .customer(customer).build();

        var amount = createDTO.getAmount();

        if (createDTO.getType().equals(TransactionType.WITHDRAW)) {
            amount = createDTO.getAmount().negate();
        }

        transaction.setAmount(amount.subtract(company.getTransactionTax()));
        transaction = transactionRepository.save(transaction);

        notifyWebhook(transaction);

        log.info("Transaction saved with success: {}", transaction.getId().toString());

        return transaction;
    }

    public void notifyWebhook(Transaction transaction) {
        log.info("Notify webhook of transactionId: {}", transaction.getId().toString());

        webWrapperClient.post(webhookURL, transaction);
    }

    public ConsolidatedTransactionResponseDTO findConsolidatedByCompanyId(String companyId){
        log.info("Finding all customers");

        List<Transaction> transactions = transactionRepository.findAllByCompanyId(UUID.fromString(companyId));

        var amountTotal = transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new ConsolidatedTransactionResponseDTO(
                transactions.stream().map(TransactionResponseDTO::fromEntity).toList(),
                amountTotal);
    }

    public Transaction findById(String id){
        log.info("Finding transaction with id: {}", id);

        return transactionRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrado"));
    }

}
