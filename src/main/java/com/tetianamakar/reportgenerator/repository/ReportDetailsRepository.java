package com.tetianamakar.reportgenerator.repository;

import com.tetianamakar.reportgenerator.entity.ReportDetails;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDetailsRepository extends MongoRepository<ReportDetails, UUID> {



}
