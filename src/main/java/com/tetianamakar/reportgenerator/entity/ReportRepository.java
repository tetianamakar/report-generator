package com.tetianamakar.reportgenerator.entity;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReportRepository extends JpaRepository<Report, UUID> {

    @Query("select r from Report r where r.company.id = ?1")
    List<Report> findReportsByCompanyId(UUID id);

}
