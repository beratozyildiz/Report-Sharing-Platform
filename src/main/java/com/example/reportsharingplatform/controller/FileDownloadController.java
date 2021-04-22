package com.example.reportsharingplatform.controller;

import com.example.reportsharingplatform.service.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("download")
public class FileDownloadController {
    @Autowired
    private FileDownloadService fileDownloadService;

    @GetMapping("file/{name:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable("name") String name) {
        Resource file = fileDownloadService.loadFile(name);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; name=\"" + file.getFilename() + "\"")
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(file);
    }
    @GetMapping("qr/{name:.+}")
    public ResponseEntity<Resource> getQR(@PathVariable("name") String name) {
        Resource file = fileDownloadService.loadQR(name);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; name=\"" + file.getFilename() + "\"")
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(file);
    }
}
