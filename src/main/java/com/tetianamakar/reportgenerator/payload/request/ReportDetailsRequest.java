package com.tetianamakar.reportgenerator.payload.request;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

@Getter
@Setter
public class ReportDetailsRequest {

    private String comments;

    private Document financialData;
}
