package com.tetianamakar.reportgenerator.controller;

import com.tetianamakar.reportgenerator.payload.request.CompanyRequest;
import com.tetianamakar.reportgenerator.payload.response.CompanyResponse;
import com.tetianamakar.reportgenerator.service.CompanyService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/all")
    public List<CompanyResponse> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping("/save")
    public void addCompany(@RequestBody CompanyRequest request) {
        companyService.addCompany(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCompany(@PathVariable String id) {
        companyService.deleteCompany(id);
    }

    @PutMapping("/update/{id}")
    public void updateCompany(@PathVariable UUID id, @RequestBody CompanyRequest request) {
        companyService.updateCompany(id, request);
    }
}
