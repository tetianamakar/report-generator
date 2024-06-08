package com.tetianamakar.reportgenerator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tetianamakar.reportgenerator.entity.Report;
import com.tetianamakar.reportgenerator.entity.ReportDetails;
import com.tetianamakar.reportgenerator.payload.request.ReportDetailsRequest;
import com.tetianamakar.reportgenerator.payload.response.ReportDetailsResponse;
import com.tetianamakar.reportgenerator.repository.ReportDetailsRepository;
import java.util.Optional;
import java.util.UUID;
import org.bson.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReportDetailsServiceTest {

    @InjectMocks
    private ReportDetailsService reportDetailsService;

    @Mock
    private ReportService reportService;

    @Mock
    private ReportDetailsRepository reportDetailsRepository;


    @Test
    @DisplayName("Should save report details")
    void testSaveReportDetails() {
        UUID reportId = UUID.randomUUID();
        ReportDetailsRequest request = new ReportDetailsRequest();
        request.setComments("Test comments");
        request.setFinancialData(Document.parse("{ \"key\": \"value\" }"));
        Report report = new Report();
        report.setId(reportId);

        when(reportService.getReportById(reportId)).thenReturn(report);

        reportDetailsService.saveReportDetails(reportId, request);

        verify(reportDetailsRepository, times(1)).save(any(ReportDetails.class));
    }

    @Test
    @DisplayName("Should get report details by report ID")
    void testGetReportDetailsByReport() {
        UUID reportId = UUID.randomUUID();
        Report report = new Report();
        report.setId(reportId);
        ReportDetails reportDetails = new ReportDetails();
        reportDetails.setReportId(reportId);
        reportDetails.setComments("Test comments");
        Document financialData = Document.parse("{ \"key\": \"value\" }");
        reportDetails.setFinancialData(financialData);

        when(reportService.getReportById(reportId)).thenReturn(report);
        when(reportDetailsRepository.findById(reportId)).thenReturn(Optional.of(reportDetails));

        ReportDetailsResponse response = reportDetailsService.getReportDetailsByReport(reportId);

        assertEquals(reportId, response.getReportId());
        assertEquals("Test comments", response.getComments());
        assertEquals(financialData, response.getFinancialData());
    }

    @Test
    @DisplayName("Should update report details")
    void testUpdateReportDetails() {
        UUID reportId = UUID.randomUUID();
        ReportDetailsRequest request = new ReportDetailsRequest();
        request.setComments("Updated comments");
        Document expectedFinancialData = Document.parse("{ \"key\": \"value\" }");
        request.setFinancialData(expectedFinancialData);
        ReportDetails reportDetails = new ReportDetails();
        reportDetails.setReportId(reportId);
        reportDetails.setComments("Test comments");
        reportDetails.setFinancialData(Document.parse("{ \"key\": \"value\" }"));


        when(reportDetailsRepository.findById(reportId)).thenReturn(Optional.of(reportDetails));

        reportDetailsService.updateReportDetails(reportId, request);

        assertEquals("Updated comments", reportDetails.getComments());
        assertEquals(expectedFinancialData, reportDetails.getFinancialData());
    }

    @Test
    @DisplayName("Should delete report details")
    void testDeleteReportDetails() {
        UUID reportId = UUID.randomUUID();
        ReportDetails reportDetails = new ReportDetails();
        reportDetails.setReportId(reportId);

        when(reportDetailsRepository.findById(reportId)).thenReturn(Optional.of(reportDetails));

        reportDetailsService.deleteReportDetails(reportId);

        verify(reportDetailsRepository, times(1)).deleteById(reportId);
    }

    @Test
    @DisplayName("Should throw exception when report details not found")
    void testGetReportDetailsByReportNotFound() {
        UUID reportId = UUID.randomUUID();
        UUID invalidReportId = UUID.randomUUID();
        Report report = new Report();
        report.setId(invalidReportId);

        when(reportService.getReportById(reportId)).thenReturn(report);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> reportDetailsService.getReportDetailsByReport(reportId));

        assertEquals("Report with such id does not exist", exception.getMessage());
    }
}
