package com.example.reportsharingplatform.controller;

import com.example.reportsharingplatform.model.Report;
import com.example.reportsharingplatform.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("list")
    public List<Report> list() {
        return reportService.getList();
    }

    @GetMapping("{id}")
    public Report getReport(@PathVariable("id") String id) {
        return reportService.getReport(id);
    }

    @GetMapping("list/actor/{id}")
    public List<Report> getListByActorId(@PathVariable("id") String id) {
        return reportService.getReportByActorId(id);
    }

    @GetMapping("public-link/{publicLink}")
    public Report getReportByPublicLink(@PathVariable("publicLink") String publicLink) {
        return reportService.getReportByPublicLink(publicLink);
    }

    @PostMapping
    public Report save(@RequestBody Report report) {
        return reportService.save(report);
    }

    @PostMapping("public-link")
    public String createPublicLink(@RequestBody Report report) {
        return reportService.savePublicLink(report);
    }

    @PostMapping("switch-status/{id}")
    public void switchStatus(@PathVariable("id") String id) {
        reportService.switchStatus(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        reportService.delete(id);
    }

    @DeleteMapping("public-link/{id}")
    public void deletePublicLink(@PathVariable("id") String id) {
        reportService.deletePublicLink(id);

    }


}
