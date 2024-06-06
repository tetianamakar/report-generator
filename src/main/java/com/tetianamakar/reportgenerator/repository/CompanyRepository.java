package com.tetianamakar.reportgenerator.repository;

import com.tetianamakar.reportgenerator.entity.Company;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

}
