package br.com.transactions.api.transaction.dto.response;

import br.com.transactions.api.transaction.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsolidatedTransactionResponseDTO {

    private List<TransactionResponseDTO> transactions;

    private BigDecimal amountTotal;

}
