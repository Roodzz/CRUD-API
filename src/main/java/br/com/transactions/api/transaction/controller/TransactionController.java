package br.com.transactions.api.transaction.controller;


import br.com.transactions.api.customer.controller.dto.request.CustomerCreateDTO;
import br.com.transactions.api.customer.controller.dto.response.CustomerResponseDTO;
import br.com.transactions.api.customer.service.CustomerService;
import br.com.transactions.api.transaction.dto.request.TransactionCreateDTO;
import br.com.transactions.api.transaction.dto.response.ConsolidatedTransactionResponseDTO;
import br.com.transactions.api.transaction.dto.response.TransactionResponseDTO;
import br.com.transactions.api.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> save(@RequestBody TransactionCreateDTO transactionCreateDTO) {
        return new ResponseEntity<>(
                TransactionResponseDTO.fromEntity(transactionService.save(transactionCreateDTO)), HttpStatus.CREATED);
    }

    @GetMapping("/empresas/{companyId}")
    public ResponseEntity<ConsolidatedTransactionResponseDTO> findConsolidatedByCompanyId(@PathVariable String companyId){
        return ResponseEntity.ok(transactionService.findConsolidatedByCompanyId(companyId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(TransactionResponseDTO.fromEntity(transactionService.findById(id)));
    }

}