package com.tetianamakar.reportgenerator.payload.response;

import java.sql.Timestamp;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponse {

    private UUID id;

    private String name;

    private String registrationNumber;

    private String address;

    private Timestamp createdAt;

}
