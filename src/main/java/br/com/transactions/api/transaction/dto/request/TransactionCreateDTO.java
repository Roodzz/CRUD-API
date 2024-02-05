package br.com.transactions.api.transaction.dto.request;

import br.com.transactions.api.transaction.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreateDTO {

    private String customerId;
    private String companyId;
    private TransactionType type;
    private BigDecimal amount;

}
