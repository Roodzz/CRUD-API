package br.com.transactions.api.customer.controller.dto.request;

import br.com.transactions.api.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateDTO {

    private String name;
    private String cpf;

    public static Customer toEntity(CustomerCreateDTO dto) {
        return Customer.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .build();
    }
}
