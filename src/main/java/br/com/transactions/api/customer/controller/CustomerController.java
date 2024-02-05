package br.com.transactions.api.customer.controller;


import br.com.transactions.api.customer.controller.dto.request.CustomerCreateDTO;
import br.com.transactions.api.customer.controller.dto.response.CustomerResponseDTO;
import br.com.transactions.api.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> save(@RequestBody CustomerCreateDTO customerCreateDTO) {
        return new ResponseEntity<>(
                CustomerResponseDTO.fromEntity(
                        customerService.save(CustomerCreateDTO.toEntity(customerCreateDTO))), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> findAll(){
        return ResponseEntity.ok(customerService.findAll().stream().map(CustomerResponseDTO::fromEntity).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(CustomerResponseDTO.fromEntity(customerService.findById(id)));
    }

}