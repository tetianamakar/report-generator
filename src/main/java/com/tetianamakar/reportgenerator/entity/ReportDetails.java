package com.tetianamakar.reportgenerator.entity;


import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Document(collection = "reportDetails")
public class ReportDetails {

    @MongoId
    @Field("report_id")
    private UUID reportId;

    @Field("financial_data")
    private org.bson.Document financialData;

    @Field("comments")
    private String comments;

}
