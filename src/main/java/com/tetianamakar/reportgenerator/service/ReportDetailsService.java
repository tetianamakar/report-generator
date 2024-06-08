package com.tetianamakar.reportgenerator.service;

import com.tetianamakar.reportgenerator.entity.Report;
import com.tetianamakar.reportgenerator.entity.ReportDetails;
import com.tetianamakar.reportgenerator.payload.request.ReportDetailsRequest;
import com.tetianamakar.reportgenerator.payload.response.ReportDetailsResponse;
import com.tetianamakar.reportgenerator.repository.ReportDetailsRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ReportDetailsService {

    private final ReportService reportService;

    private final ReportDetailsRepository reportDetailsRepository;


    public ReportDetailsService(ReportService reportService, ReportDetailsRepository reportDetailsRepository) {
        this.reportService = reportService;
        this.reportDetailsRepository = reportDetailsRepository;
    }

    public void saveReportDetails(UUID reportId, ReportDetailsRequest request) {
        ReportDetails reportDetails = new ReportDetails();
        reportDetails.setComments(request.getComments());
        Report report = reportService.getReportById(reportId);
        reportDetails.setReportId(report.getId());
        reportDetails.setFinancialData(request.getFinancialData());
        reportDetailsRepository.save(reportDetails);
    }

    public ReportDetailsResponse getReportDetailsByReport(UUID reportId) {
        Report report = reportService.getReportById(reportId);
        ReportDetails reportDetails = getReportDetailsByReportId(report.getId());
        ReportDetailsResponse response = new ReportDetailsResponse();
        response.setReportId(report.getId());
        response.setComments(reportDetails.getComments());
        response.setFinancialData(reportDetails.getFinancialData());
        return response;
    }


    public void updateReportDetails(UUID reportId, ReportDetailsRequest request) {
        ReportDetails reportDetails = getReportDetailsByReportId(reportId);
        reportDetails.setFinancialData(request.getFinancialData());
        reportDetails.setComments(request.getComments());
        reportDetailsRepository.save(reportDetails);
    }

    public void deleteReportDetails(UUID reportId) {
        ReportDetails reportDetails = getReportDetailsByReportId(reportId);
        reportDetailsRepository.deleteById(reportDetails.getReportId());
    }

    private ReportDetails getReportDetailsByReportId(UUID reportId) {
        return reportDetailsRepository.findById(reportId)
            .orElseThrow(() -> new RuntimeException("Report with such id does not exist"));
    }

}
