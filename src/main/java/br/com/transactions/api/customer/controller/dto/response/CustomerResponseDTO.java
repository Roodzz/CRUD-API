package br.com.transactions.api.customer.controller.dto.response;

import br.com.transactions.api.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {

    private String id;
    private String name;
    private String cpf;


    public static CustomerResponseDTO fromEntity(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId().toString())
                .name(customer.getName())
                .cpf(customer.getCpf())
                .build();
    }
}
