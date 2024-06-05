package com.tetianamakar.reportgenerator.payload.response;

import com.tetianamakar.reportgenerator.entity.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponse {

    private String id;

    private String name;

    private String registrationNumber;

    private String address;

    private String createdAt;

    public CompanyResponse(Company company) {
        this.id = company.getId().toString();
        this.name = company.getName();
        this.registrationNumber = company.getRegistrationNumber();
        this.address = company.getAddress();
        this.createdAt = company.getCreatedAt().toString();
    }
}
