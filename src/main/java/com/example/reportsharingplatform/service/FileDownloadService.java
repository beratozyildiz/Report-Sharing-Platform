package com.example.reportsharingplatform.service;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileDownloadService {
    private final Path rootLocation= Paths.get("/Users/beratozyildiz/Downloads/uploads/");
    private final Path rootLocation2= Paths.get("/Users/beratozyildiz/Downloads/uploads/qr/");
    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else{
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }
    public Resource loadQR(String filename) {
        try {
            String fullFile="qr-"+filename+".png";
            Path file = rootLocation2.resolve(fullFile);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else{
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }
}
