package com.example.reportsharingplatform.service;

import com.example.reportsharingplatform.model.*;
import com.example.reportsharingplatform.repository.AccountRepository;
import com.example.reportsharingplatform.repository.LinkRepository;
import com.example.reportsharingplatform.repository.LogRepository;
import com.example.reportsharingplatform.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private LogService logService;
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Report save(Report report) {
        Log log=logService.tempLog(report.getActor());
        if (reportRepository.findReportById(report.getId()) != null)
            log.setType(Log.Type.reportUpdate);
        else
            log.setType(Log.Type.reportCreate);
        logRepository.save(log);
        if (report.getHistory() == null) {
            List<Log> list = new ArrayList<Log>();
            report.setHistory(list);
        }
        report.getHistory().add(log);
        reportRepository.save(report);
        return reportRepository.save(report);
    }
    public String savePublicLink(Report report) {
        Log log=logService.tempLog(report.getActor());
        if (reportRepository.findReportById(report.getId()) != null)
            log.setType(Log.Type.publicLinkUpdate);
        else
            log.setType(Log.Type.publicLinkCreate);
        logRepository.save(log);
        report.getHistory().add(log);
        report.setPublicLink(UUID.randomUUID().toString());
        reportRepository.save(report);
        return report.getPublicLink();
    }
    public void switchStatus(String reportId) {
        Report report = reportRepository.findReportById(reportId);
        Link link = report.getLinkProperties();
        if (link != null) {
            link.setStatusOfThePublicLink(!link.isStatusOfThePublicLink());
            linkRepository.save(link);
            Log log=logService.tempLog(report.getActor());
            if (link.isStatusOfThePublicLink())
                log.setType(Log.Type.linkPropertiesActivatedLink);
            else
                log.setType(Log.Type.linkPropertiesDeactivatedLink);
            logRepository.save(log);
            report.getHistory().add(log);
            reportRepository.save(report);
        }
    }

    public void deletePublicLink(String reportId) {
        Report report = reportRepository.findReportById(reportId);
        if (report.getPublicLink() != null) {
            report.setPublicLink(null);
            Log log=logService.tempLog(report.getActor());
            log.setType(Log.Type.publicLinkDelete);
            logRepository.save(log);
            report.getHistory().add(log);
            reportRepository.save(report);
        }
    }

    public void delete(String reportId) {
        Report report = reportRepository.findReportById(reportId);
        reportRepository.delete(report);
        Log log = logService.tempLog(report.getActor());
        log.setType(Log.Type.reportDelete);
        logRepository.save(log);

    }

    public List<Report> getList() {
        return reportRepository.findAll();
    }

    public Report getReport(String reportId) {
        Report report = reportRepository.findReportById(reportId);
        Log log=logService.tempLog(report.getActor());
        log.setType(Log.Type.reportRead);
        logRepository.save(log);
        report.getHistory().add(log);

        return report;
    }

    public Report getReportByPublicLink(String publiclink) {
        Report report = reportRepository.findReportByPublicLink(publiclink);
        Log log=logService.tempLog(report.getActor());
        log.setType(Log.Type.reportRead);
        logRepository.save(log);
        report.getHistory().add(log);
        reportRepository.save(report);
        return report;
    }

    public List<Report> getReportByActorId(String id) {
        List<Report> list = reportRepository.findReportByActorId(id);
        Log log=logService.tempLog(accountRepository.findAccountById(id));
        log.setType(Log.Type.reportRead);
        logRepository.save(log);
        return list;
    }
}
