package com.tetianamakar.reportgenerator.payload.request;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDetailsRequest {

    private UUID reportId;

    private String comments;

}
