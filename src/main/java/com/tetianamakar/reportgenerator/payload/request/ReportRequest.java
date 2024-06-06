package com.tetianamakar.reportgenerator.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportRequest {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp reportDate;

    private BigDecimal totalRevenue;

    private BigDecimal netProfit;

    private UUID companyId;

}
