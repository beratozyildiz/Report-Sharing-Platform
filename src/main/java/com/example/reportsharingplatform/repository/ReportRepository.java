package com.example.reportsharingplatform.repository;

import com.example.reportsharingplatform.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReportRepository extends MongoRepository<Report, String> {
    Report findReportById(String id);

    Report findReportByDocumentId(String documentId);

    Report findReportByPublicLink(String publicLink);

    List<Report> findReportByActorId(String id);
}
