package br.com.transactions.api.transaction.dto.request;

import br.com.transactions.api.transaction.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreateDTO {

    private String customerId;
    private String companyId;
    private TransactionType type;
    private BigDecimal amount;

}
