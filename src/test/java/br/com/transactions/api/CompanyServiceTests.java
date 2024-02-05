package br.com.transactions.api;


import br.com.transactions.api.company.entity.Company;
import br.com.transactions.api.company.repository.CompanyRepository;
import br.com.transactions.api.company.service.CompanyService;
import br.com.transactions.api.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CompanyServiceTests {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;


    @Test
    void shouldCreateCompanyWithSuccess() {
        var company = Mockito.mock(Company.class);

        when(companyRepository.save(any())).thenReturn(company);

        var companyCreated = companyService.save(company);

        verify(companyRepository, atLeastOnce()).save(any());
        assertEquals(companyCreated.getId(), company.getId());
        assertEquals(companyCreated.getName(), company.getName());
    }

    @Test
    void shouldFindCompanyWithSuccess() {
        var company = Mockito.mock(Company.class);

        when(companyRepository.findById(any())).thenReturn(Optional.of(company));

        var existentCompany = companyService.findById(UUID.randomUUID().toString());

        verify(companyRepository, atLeastOnce()).findById(any());
        assertEquals(existentCompany.getId(), company.getId());
        assertEquals(existentCompany.getName(), company.getName());
    }

    @Test
    void shouldFindCompanyIfNotExistsThrowError() {
        when(companyRepository.findById(any())).thenThrow(new NotFoundException("Não encontrado"));

        assertThrows(NotFoundException.class, () -> companyService.findById(UUID.randomUUID().toString()));
    }


}
