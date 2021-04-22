package com.example.reportsharingplatform;
import com.example.reportsharingplatform.model.*;
import com.example.reportsharingplatform.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;


@SpringBootTest
class ReportsharingplatformApplicationTests {

    @Autowired
    AccountService accountService;
    @Autowired
    LogService logService;
    @Autowired
    ReportService reportService;
    @Autowired
    LinkService linkService;
    @Autowired
    RoleService roleService;

    @Test
    void contextLoads() throws IOException {

    }

}

