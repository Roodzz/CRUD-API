package br.com.transactions.api.company.controller.dto.response;

import br.com.transactions.api.company.entity.Company;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponseDTO {

    private String id;
    private String name;
    private String cnpj;
    private BigDecimal transactionTax;


    public static CompanyResponseDTO fromEntity(Company company) {
        return CompanyResponseDTO.builder()
                .id(company.getId().toString())
                .name(company.getName())
                .cnpj(company.getCnpj())
                .transactionTax(company.getTransactionTax())
                .build();
    }
}
