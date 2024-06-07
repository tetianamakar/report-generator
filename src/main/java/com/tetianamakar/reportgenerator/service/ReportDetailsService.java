package com.tetianamakar.reportgenerator.service;

import com.tetianamakar.reportgenerator.converter.EntityConverter;
import com.tetianamakar.reportgenerator.entity.Report;
import com.tetianamakar.reportgenerator.entity.ReportDetails;
import com.tetianamakar.reportgenerator.payload.request.ReportDetailsRequest;
import com.tetianamakar.reportgenerator.payload.response.ReportDetailsResponse;
import com.tetianamakar.reportgenerator.payload.response.ReportDetailsWithoutReportResponse;
import com.tetianamakar.reportgenerator.repository.ReportDetailsRepository;
import java.util.List;
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
        reportDetails.setReportId(report.getId().toString());
//        reportDetails.setFinancialData(new JsonObject("{\"key1\": \"value1\", \"key2\": 42}"));
        reportDetailsRepository.save(reportDetails);
    }

    public List<ReportDetailsWithoutReportResponse> getReportsDetails() {
        List<ReportDetails> reportDetails = reportDetailsRepository.findAll();
        return EntityConverter.convertReportDetails(reportDetails);
    }


    public ReportDetailsResponse getReportDetailsByReport(UUID reportId) {
        Report report = reportService.getReportById(reportId);
        ReportDetails reportDetails = getReportDetailsByReportId(report.getId());
        ReportDetailsResponse response = new ReportDetailsResponse();
        response.setReportId(report.getId());
        response.setComments(reportDetails.getComments());
        return response;
    }

    private ReportDetails getReportDetailsByReportId(UUID reportId) {
        return reportDetailsRepository.findById(reportId)
            .orElseThrow(() -> new RuntimeException("ReportDetails with such id does not exist"));
    }


}
