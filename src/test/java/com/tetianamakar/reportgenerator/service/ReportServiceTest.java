package com.tetianamakar.reportgenerator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tetianamakar.reportgenerator.entity.Company;
import com.tetianamakar.reportgenerator.entity.Report;
import com.tetianamakar.reportgenerator.payload.request.ReportRequest;
import com.tetianamakar.reportgenerator.payload.response.ReportResponse;
import com.tetianamakar.reportgenerator.repository.ReportRepository;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
class ReportServiceTest {

    @InjectMocks
    private ReportService reportService;

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private CompanyService companyService;

    @Test
    @DisplayName("Should return reports for given company ID")
    void testGetReports() {
        UUID companyId = UUID.randomUUID();
        Company company = createCompany(companyId);
        List<Report> reports = Arrays.asList(
            createReport(UUID.randomUUID()),
            createReport(UUID.randomUUID())
        );

        when(companyService.getCompanyById(companyId)).thenReturn(company);
        when(reportRepository.findReportsByCompanyId(companyId)).thenReturn(reports);

        List<ReportResponse> responses = reportService.getReports(companyId);

        assertEquals(2, responses.size());
        verify(companyService, times(1)).getCompanyById(companyId);
        verify(reportRepository, times(1)).findReportsByCompanyId(companyId);
    }

    @Test
    @DisplayName("Should save a new report")
    void testSaveReport() {
        UUID companyId = UUID.randomUUID();
        ReportRequest request = new ReportRequest();
        request.setCompanyId(companyId);
        request.setReportDate(new Timestamp(System.currentTimeMillis()));
        request.setTotalRevenue(new BigDecimal("1500.00"));
        request.setNetProfit(new BigDecimal("150.00"));

        reportService.saveReport(request);

        verify(reportRepository, times(1)).save(any(Report.class));
    }

    @Test
    @DisplayName("Should update an existing report")
    void testUpdateReport() {
        UUID reportId = UUID.randomUUID();
        UUID companyId = UUID.randomUUID();
        Report report = createReport(reportId);
        ReportRequest request = new ReportRequest();
        request.setCompanyId(companyId);
        request.setReportDate(new Timestamp(System.currentTimeMillis()));
        request.setTotalRevenue(new BigDecimal("2000.00"));
        request.setNetProfit(new BigDecimal("200.00"));

        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));

        reportService.updateReport(reportId, request);

        verify(reportRepository, times(1)).save(report);
        assertEquals(request.getReportDate(), report.getReportDate());
        assertEquals(request.getTotalRevenue(), report.getTotalRevenue());
        assertEquals(request.getNetProfit(), report.getNetProfit());
    }

    @Test
    @DisplayName("Should delete a report by ID")
    void testDeleteReport() {
        UUID reportId = UUID.randomUUID();

        reportService.deleteReport(reportId);

        verify(reportRepository, times(1)).deleteById(reportId);
    }

    @Test
    @DisplayName("Should return a report by ID")
    void testGetReportById() {
        UUID reportId = UUID.randomUUID();
        Report report = createReport(reportId);

        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));

        Report foundReport = reportService.getReportById(reportId);

        assertEquals(report, foundReport);
    }

    @Test
    @DisplayName("Should throw an exception when report not found by ID")
    void testGetReportByIdNotFound() {
        UUID reportId = UUID.randomUUID();
        when(reportRepository.findById(reportId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> reportService.getReportById(reportId));

        assertEquals("Report with such id does not exist", exception.getMessage());
    }

    private Company createCompany(UUID uuid) {
        Company company = new Company();
        company.setId(uuid);
        company.setName("Test Company");
        company.setRegistrationNumber("1234567890");
        company.setAddress("Test Address");
        return company;
    }

    private Report createReport(UUID id) {
        Report report = new Report();
        report.setId(id);
        report.setCompany(createCompany(UUID.randomUUID()));
        report.setReportDate(new Timestamp(System.currentTimeMillis()));
        report.setTotalRevenue(new BigDecimal("1000.00"));
        report.setNetProfit(new BigDecimal("100.00"));
        return report;
    }
}
