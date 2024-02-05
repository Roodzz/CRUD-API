package br.com.transactions.api.company.controller;


import br.com.transactions.api.company.controller.dto.request.CompanyCreateDTO;
import br.com.transactions.api.company.controller.dto.response.CompanyResponseDTO;
import br.com.transactions.api.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> save(@RequestBody CompanyCreateDTO companyCreateDTO) {
        return new ResponseEntity<>(
                CompanyResponseDTO.fromEntity(
                        companyService.save(CompanyCreateDTO.toEntity(companyCreateDTO))), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponseDTO>> findAll(){
        return ResponseEntity.ok(companyService.findAll().stream().map(CompanyResponseDTO::fromEntity).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(CompanyResponseDTO.fromEntity(companyService.findById(id)));
    }

}