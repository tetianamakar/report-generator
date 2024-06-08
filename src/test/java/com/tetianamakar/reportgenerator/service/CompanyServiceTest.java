package com.tetianamakar.reportgenerator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tetianamakar.reportgenerator.entity.Company;
import com.tetianamakar.reportgenerator.payload.request.CompanyRequest;
import com.tetianamakar.reportgenerator.payload.response.CompanyResponse;
import com.tetianamakar.reportgenerator.repository.CompanyRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository repository;

    @Test
    @DisplayName("Should return all companies")
    void testGetAllCompanies() {
        List<Company> companies = Arrays.asList(
            createCompany(UUID.randomUUID(), "Company A", "123456", "Address A"),
            createCompany(UUID.randomUUID(), "Company B", "789012", "Address B")
        );
        when(repository.findAll()).thenReturn(companies);

        List<CompanyResponse> responses = companyService.getAllCompanies();

        assertEquals(2, responses.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should add a company")
    void testAddCompany() {
        CompanyRequest request = new CompanyRequest();
        request.setName("Company A");
        request.setRegistrationNumber("123456");
        request.setAddress("Address A");

        companyService.addCompany(request);

        verify(repository, times(1)).save(any(Company.class));
    }

    @Test
    @DisplayName("Should delete a company by ID")
    void testDeleteCompany() {
        UUID id = UUID.randomUUID();

        companyService.deleteCompany(id.toString());

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Should update a company")
    void testUpdateCompany() {
        UUID id = UUID.randomUUID();
        CompanyRequest request = new CompanyRequest();
        request.setName("Updated Company");
        request.setRegistrationNumber("654321");
        request.setAddress("Updated Address");

        Company company = createCompany(id, "Old Company", "123456", "Old Address");
        when(repository.findById(id)).thenReturn(Optional.of(company));

        companyService.updateCompany(id, request);

        verify(repository, times(1)).save(company);
        assertEquals(request.getName(), company.getName());
        assertEquals(request.getRegistrationNumber(), company.getRegistrationNumber());
        assertEquals(request.getAddress(), company.getAddress());
    }

    @Test
    @DisplayName("Should return a company by ID")
    void testGetCompanyById() {
        UUID id = UUID.randomUUID();
        Company company = createCompany(id, "Company A", "123456", "Address A");
        when(repository.findById(id)).thenReturn(Optional.of(company));

        Company foundCompany = companyService.getCompanyById(id);

        assertEquals(company, foundCompany);
    }

    @Test
    @DisplayName("Should throw an exception when company not found by ID")
    void testGetCompanyByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> companyService.getCompanyById(id));

        assertEquals("Company with id " + id + " does not exist", exception.getMessage());
    }

    private Company createCompany(UUID id, String name, String registrationNumber, String address) {
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setRegistrationNumber(registrationNumber);
        company.setAddress(address);
        return company;
    }
}
