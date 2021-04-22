package com.example.reportsharingplatform.controller;

import com.example.reportsharingplatform.model.Reference;
import com.example.reportsharingplatform.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("document")
public class ReferenceController {
    @Autowired
    ReferenceService referenceService;

    @GetMapping("list")
    public List<Reference> list() {
        return referenceService.getList();
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        referenceService.delete(id);
    }
}
