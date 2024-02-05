package br.com.transactions.api.transaction.dto.response;

import br.com.transactions.api.company.controller.dto.response.CompanyResponseDTO;
import br.com.transactions.api.company.entity.Company;
import br.com.transactions.api.transaction.entity.Transaction;
import br.com.transactions.api.transaction.entity.TransactionType;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionResponseDTO {

    private String id;
    private String customerId;
    private String companyId;
    private TransactionType type;
    private BigDecimal amount;

    public static TransactionResponseDTO fromEntity(Transaction transaction) {
        return TransactionResponseDTO.builder()
                .id(transaction.getId().toString())
                .customerId(transaction.getCustomer().getId().toString())
                .companyId(transaction.getCompany().getId().toString())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .build();
    }
}
