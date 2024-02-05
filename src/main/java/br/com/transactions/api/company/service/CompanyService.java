package br.com.transactions.api.company.service;

import br.com.transactions.api.company.entity.Company;
import br.com.transactions.api.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company save(Company company) {
        log.info("Saving new company with name: {}", company.getName());

        return companyRepository.save(company);
    }

    public List<Company> findAll(){
        log.info("Finding all companies");

        return companyRepository.findAll();
    }
    public Company findById(String id){
        log.info("Finding company with id: {}", id);

        return companyRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));
    }

}
