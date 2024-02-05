package br.com.transactions.api.company.controller.dto.request;

import br.com.transactions.api.company.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyCreateDTO {

    private String name;
    private String cnpj;
    private BigDecimal transactionTax;

    public static Company toEntity(CompanyCreateDTO dto) {
        return Company.builder()
                .name(dto.getName())
                .cnpj(dto.getCnpj())
                .transactionTax(dto.getTransactionTax())
                .build();
    }
}
