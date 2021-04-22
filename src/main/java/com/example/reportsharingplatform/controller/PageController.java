package com.example.reportsharingplatform.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PageController {
    @GetMapping({"/","/administrator/{param}/account-list", "/administrator/{param}/report-list", "/administrator/{param}/log-list",
            "/administrator/{param}/report/{param}/history-list","/administrator/{param}/create-account","/administrator/{param}/account/{param}",
            "evaluator/{param}/report-list","/evaluator/{param}/create-report","/upload","/evaluator/{param}/report/{param}/history-list",
            "/evaluator/{param}/report/{param}/create-link-properties","/evaluator/{param}/report-list",
            "/pr/{param}","/ur/{param}","/pr/{param}/download/{param}","/evaluator/home","/administrator/home","/validation/{param}"})
    public String index() {
        return "index";
    }
}
