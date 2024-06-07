package com.tetianamakar.reportgenerator.entity;


import lombok.Getter;
import lombok.Setter;
import org.bson.json.JsonObject;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Document(collection = "reportDetails")
public class ReportDetails {

    @MongoId
    @Field("report_id")
    private String reportId;

    @Field("financial_data")
    private JsonObject financialData;

    @Field("comments")
    private String comments;

}
