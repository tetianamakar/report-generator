package com.tetianamakar.reportgenerator.repository;

import com.tetianamakar.reportgenerator.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    @Transactional
    @Modifying
    @Query("update Company c set c.name = ?1 where c.id = ?2")
    void updateNameById(String name);
}
