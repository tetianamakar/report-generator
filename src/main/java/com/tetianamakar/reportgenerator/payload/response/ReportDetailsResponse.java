package com.tetianamakar.reportgenerator.payload.response;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

@Getter
@Setter
public class ReportDetailsResponse {

    private UUID reportId;

    private String comments;

    private Document financialData;
}
