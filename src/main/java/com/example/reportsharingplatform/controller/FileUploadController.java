package com.example.reportsharingplatform.controller;

import com.example.reportsharingplatform.model.Reference;
import com.example.reportsharingplatform.service.FileUploadService;
import com.example.reportsharingplatform.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/upload")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping
    public Reference uploadFile(@RequestParam("files") MultipartFile[] files) throws IOException {
        return fileUploadService.uploadFile(files);
    }
}
