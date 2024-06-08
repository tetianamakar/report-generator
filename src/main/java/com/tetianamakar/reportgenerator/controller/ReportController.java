package com.tetianamakar.reportgenerator.controller;

import com.tetianamakar.reportgenerator.payload.request.ReportRequest;
import com.tetianamakar.reportgenerator.payload.response.ReportResponse;
import com.tetianamakar.reportgenerator.service.ReportService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/all/{companyId}")
    public List<ReportResponse> getReports(@PathVariable UUID companyId) {
        return reportService.getReports(companyId);
    }

    @PostMapping("/save")
    public void saveReport(@RequestBody ReportRequest request) {
        reportService.saveReport(request);
    }

    @PutMapping("/update/{reportId}")
    public void updateReport(@PathVariable UUID reportId, @RequestBody ReportRequest request) {
        reportService.updateReport(reportId, request);
    }

    @DeleteMapping("/delete/{reportId}")
    public void deleteReport(@PathVariable UUID reportId) {
        reportService.deleteReport(reportId);
    }

}
