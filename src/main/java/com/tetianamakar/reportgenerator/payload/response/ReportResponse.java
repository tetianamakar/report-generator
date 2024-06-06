package com.tetianamakar.reportgenerator.payload.response;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportResponse {

    private UUID id;

    private Timestamp reportDate;

    private BigDecimal totalRevenue;

    private BigDecimal netProfit;

    private UUID companyId;

}
