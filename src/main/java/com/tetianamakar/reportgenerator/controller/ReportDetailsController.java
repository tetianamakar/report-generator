package com.tetianamakar.reportgenerator.controller;

import com.tetianamakar.reportgenerator.payload.request.ReportDetailsRequest;
import com.tetianamakar.reportgenerator.payload.response.ReportDetailsResponse;
import com.tetianamakar.reportgenerator.service.ReportDetailsService;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report-details")
public class ReportDetailsController {

    private final ReportDetailsService reportDetailsService;

    public ReportDetailsController(ReportDetailsService reportDetailsService) {
        this.reportDetailsService = reportDetailsService;
    }

    @PostMapping("/save/{reportId}")
    public void saveReportDetails(@PathVariable UUID reportId, @RequestBody ReportDetailsRequest request) {
        reportDetailsService.saveReportDetails(reportId, request);
    }

    @GetMapping("/get/{reportId}")
    public ReportDetailsResponse getReportDetailsByReport(@PathVariable UUID reportId) {
        return reportDetailsService.getReportDetailsByReport(reportId);
    }
}
