package com.example.reportsharingplatform.controller;

import com.example.reportsharingplatform.service.QRService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.FileSystems;
import java.nio.file.Path;

@RestController
@RequestMapping("qr")
public class QRController {
    @Autowired
    QRService qrService;

    @RequestMapping(value = "/generate/{publicLink}", method = RequestMethod.GET)
    public void generateQR(@PathVariable("publicLink") String publicLink) {
        qrService.generateQRCode(publicLink);
    }
}
