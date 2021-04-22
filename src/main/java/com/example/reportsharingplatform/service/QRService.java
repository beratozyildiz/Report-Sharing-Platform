package com.example.reportsharingplatform.service;

import com.example.reportsharingplatform.controller.QRController;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public class QRService {
    private static Logger logger = LoggerFactory.getLogger(QRService.class);

    public void generateQRCode(String dataString) {
        try {
            logger.info("In generateQRCode() called in DVController.");
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            String fullData="http://localhost:8080/pr/"+dataString;
            BitMatrix bitMatrix = qrCodeWriter.encode(fullData, BarcodeFormat.QR_CODE, 300, 300);
            Path path = FileSystems.getDefault().getPath("/Users/beratozyildiz/Downloads/uploads/qr/qr-"+dataString+".png");
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            System.out.println("Successfully QR code generated in path.");
        } catch (Exception e) {
            logger.error("Exception in generateQRCode in QRController", e);
            e.printStackTrace();
        }
    }
}
