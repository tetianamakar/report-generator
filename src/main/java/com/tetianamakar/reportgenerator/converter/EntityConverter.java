package com.tetianamakar.reportgenerator.converter;

import com.tetianamakar.reportgenerator.entity.Company;
import com.tetianamakar.reportgenerator.entity.Report;
import com.tetianamakar.reportgenerator.entity.ReportDetails;
import com.tetianamakar.reportgenerator.entity.User;
import com.tetianamakar.reportgenerator.payload.response.CompanyResponse;
import com.tetianamakar.reportgenerator.payload.response.ReportDetailsWithoutReportResponse;
import com.tetianamakar.reportgenerator.payload.response.ReportResponse;
import com.tetianamakar.reportgenerator.payload.response.UserResponse;
import java.util.List;
import java.util.stream.Collectors;

public class EntityConverter {

    public static UserResponse convertUser(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setAge(user.getAge());
        return response;
    }

    public static List<CompanyResponse> convertCompanies(List<Company> companies) {
        return companies.stream()
                .map(EntityConverter::convertCompany)
                .collect(Collectors.toList());
    }

    private static CompanyResponse convertCompany(Company company) {
        CompanyResponse response = new CompanyResponse();
        response.setId(company.getId());
        response.setName(company.getName());
        response.setRegistrationNumber(company.getRegistrationNumber());
        response.setCreatedAt(company.getCreatedAt());
        response.setAddress(company.getAddress());
        return response;
    }

    public static List<ReportResponse> convertReports(List<Report> reports) {
        return reports
            .stream()
            .map(EntityConverter::convertReport)
            .collect(Collectors.toList());
    }

    public static ReportResponse convertReport(Report report) {
        ReportResponse response = new ReportResponse();
        response.setId(report.getId());
        response.setReportDate(report.getReportDate());
        response.setTotalRevenue(report.getTotalRevenue());
        response.setNetProfit(report.getNetProfit());
        response.setCompanyId(report.getCompany().getId());
        return response;
    }

    public static List<ReportDetailsWithoutReportResponse> convertReportDetails(List<ReportDetails> reportDetails) {
        return reportDetails.stream()
            .map(EntityConverter::convertReportDetailPiece)
            .collect(Collectors.toList());
    }

    public static ReportDetailsWithoutReportResponse convertReportDetailPiece(ReportDetails reportDetails) {
        ReportDetailsWithoutReportResponse reportDetailsWithoutReportResponse = new ReportDetailsWithoutReportResponse();
        reportDetailsWithoutReportResponse.setComments(reportDetails.getComments());
        return reportDetailsWithoutReportResponse;
    }
}
