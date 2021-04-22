package com.example.reportsharingplatform.controller;


import com.example.reportsharingplatform.model.Link;
import com.example.reportsharingplatform.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("{id}")
    public Link getLink(@PathVariable("id") String id) {
        return linkService.getLink(id);
    }

    @PostMapping
    public Link save(@RequestBody Link link) {
        return linkService.save(link);
    }
}
