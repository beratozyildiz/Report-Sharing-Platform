package com.example.reportsharingplatform.service;

import com.example.reportsharingplatform.model.Reference;
import com.example.reportsharingplatform.repository.ReferenceRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FileUploadService {
    @Autowired
    private ReferenceRepository referenceRepository;

    public Reference uploadFile(MultipartFile[] files) throws IOException {
        int flag=0;
        for (MultipartFile checkfile : files){
            String lastpart = getFileExtension(checkfile);
            if (!lastpart.equals("docx") && !lastpart.equals("doc") && !lastpart.equals("pdf"))
                flag=1;
        }
        if (flag==0){
            if (files.length > 1) {
            String s = UUID.randomUUID().toString();
            Date date = new Date();
            FileOutputStream fos = new FileOutputStream("/Users/beratozyildiz/Downloads/uploads/" + "report-" + s + ".zip");
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            for (MultipartFile file : files) {
                File fileToZip = multipartToFile(file, file.getOriginalFilename());
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            }
            zipOut.close();
            fos.close();
            File f = new File("/Users/beratozyildiz/Downloads/uploads/" + "report-" + s + ".zip");
            String key = DigestUtils.sha256Hex(new FileInputStream(f));
            return buildReference(f, key);
        } else {
            Reference reference = new Reference();
            for (MultipartFile file : files) {
                String a = UUID.randomUUID().toString();
                String returnValue = getFileExtension(file);
                file.transferTo(new File("/Users/beratozyildiz/Downloads/uploads/" + "report-" + a + "." + returnValue));
                String key = DigestUtils.sha256Hex(new FileInputStream("/Users/beratozyildiz/Downloads/uploads/" + "report-" + a + "." + returnValue));
                reference
                        .setId(UUID.randomUUID().toString())
                        .setName("report-" + a + "." + returnValue)
                        .setPath("/Users/beratozyildiz/Downloads/uploads/" + "report-" + a + "." + returnValue)
                        .setKey(key)
                        .setSize(file.getSize());
                if (returnValue.equals("docx") || returnValue.equals("doc"))
                    reference.setType(Reference.Type.doc);
                else if (returnValue.equals("pdf"))
                    reference.setType(Reference.Type.pdf);
                else
                    reference.setType(null);
            }
            referenceRepository.save(reference);
            return reference;
        }
    }
        return null;
    }

    public File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
        multipart.transferTo(convFile);
        return convFile;
    }

    public Reference buildReference(File file, String key) {
        Reference reference = new Reference()
                .setId(UUID.randomUUID().toString())
                .setName(file.getName())
                .setPath(file.getPath())
                .setSize(file.length())
                .setKey(key)
                .setType(Reference.Type.zip);
        referenceRepository.save(reference);
        return reference;
    }

    public String getFileExtension(MultipartFile file) {
        if (file == null) {
            return "";
        }
        String name = file.getOriginalFilename();
        int i = name.lastIndexOf('.');
        String ext = i > 0 ? name.substring(i + 1) : "";
        return ext;
    }
}
