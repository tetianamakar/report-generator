package com.tetianamakar.reportgenerator.service;

import com.tetianamakar.reportgenerator.converter.EntityConverter;
import com.tetianamakar.reportgenerator.entity.Company;
import com.tetianamakar.reportgenerator.payload.request.CompanyRequest;
import com.tetianamakar.reportgenerator.payload.response.CompanyResponse;
import com.tetianamakar.reportgenerator.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<CompanyResponse> getAllCompanies() {
        List<Company> companies = repository.findAll();
        return EntityConverter.convertCompanies(companies);
    }

    public void addCompany(CompanyRequest request) {
        Company company = new Company();
        company.setName(request.getName());
        company.setRegistrationNumber(request.getRegistrationNumber());
        company.setAddress(request.getAddress());
        repository.save(company);
    }

    public void deleteCompany(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    public void updateCompany(String id) {
        repository.updateNameById(id);
    }
}
