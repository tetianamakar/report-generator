package com.tetianamakar.reportgenerator.service;

import com.tetianamakar.reportgenerator.converter.EntityConverter;
import com.tetianamakar.reportgenerator.entity.Company;
import com.tetianamakar.reportgenerator.entity.Report;
import com.tetianamakar.reportgenerator.entity.ReportRepository;
import com.tetianamakar.reportgenerator.payload.request.ReportRequest;
import com.tetianamakar.reportgenerator.payload.response.ReportResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    private final CompanyService companyService;

    public ReportService(ReportRepository reportRepository, CompanyService companyService) {
        this.reportRepository = reportRepository;
        this.companyService = companyService;
    }

    public List<ReportResponse> getReports(String id) {
        List<Report> reports = reportRepository.findReportsByCompanyId(UUID.fromString(id));
        return EntityConverter.convertReports(reports);
    }

    public void saveReport(ReportRequest request) {
        Report report = new Report();
        report.setReportDate(request.getReportDate());
        report.setTotalRevenue(request.getTotalRevenue());
        report.setNetProfit(request.getNetProfit());
        Company company = companyService.getCompanyById(request.getCompanyId());
        report.setCompany(company);
        reportRepository.save(report);
    }

    public void updateReport(UUID reportId, ReportRequest request) {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report with such id does not exist"));
        report.setReportDate(request.getReportDate());
        report.setTotalRevenue(request.getTotalRevenue());
        report.setNetProfit(request.getNetProfit());

        Company company = companyService.getCompanyById(request.getCompanyId());
        report.setCompany(company);
        reportRepository.save(report);
    }

    public void deleteReport(UUID reportId) {
        reportRepository.deleteById(reportId);
    }
}

