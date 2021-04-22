package com.example.reportsharingplatform.service;

import com.example.reportsharingplatform.model.Account;
import com.example.reportsharingplatform.model.Link;
import com.example.reportsharingplatform.model.Log;
import com.example.reportsharingplatform.repository.LinkRepository;
import com.example.reportsharingplatform.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private LogRepository logRepository;

    public Link save(Link link) {
        Log log = new Log()
                .setId(UUID.randomUUID().toString())
                .setDate(new Date())
                .setActor(null);
        if (linkRepository.findLinkById(link.getId()) != null)
            log.setType(Log.Type.linkPropertiesUpdate);
        else
            log.setType(Log.Type.linkPropertiesCreate);
        logRepository.save(log);
        return linkRepository.save(link);
    }

    public List<Link> getList() {
        Log log = new Log()
                .setId(UUID.randomUUID().toString())
                .setDate(new Date())
                .setActor(null)
                .setType(Log.Type.linkPropertiesRead);
        logRepository.save(log);
        return linkRepository.findAll();
    }

    public Link getLink(String linkId) {
        Log log = new Log()
                .setId(UUID.randomUUID().toString())
                .setDate(new Date())
                .setActor(null)
                .setType(Log.Type.linkPropertiesRead);
        logRepository.save(log);
        return linkRepository.findLinkById(linkId);

    }


    public void delete(String linkId) {
        Link link = linkRepository.findLinkById(linkId);
        Log log = new Log()
                .setId(UUID.randomUUID().toString())
                .setDate(new Date())
                .setActor(null)
                .setType(Log.Type.linkPropertiesDelete);
        logRepository.save(log);
        linkRepository.delete(link);
    }

    public List<Link> getLinksByReusabilityOfTheLink(boolean reusabilityOfTheLink) {
        Log log = new Log()
                .setId(UUID.randomUUID().toString())
                .setDate(new Date())
                .setActor(null)
                .setType(Log.Type.linkPropertiesRead);
        logRepository.save(log);
        return linkRepository.findLinksByReusabilityOfTheLink(reusabilityOfTheLink);
    }

    public List<Link> getLinksByStatusOfThePublicLink(boolean statusOfThePublicLink) {
        Log log = new Log()
                .setId(UUID.randomUUID().toString())
                .setDate(new Date())
                .setActor(null)
                .setType(Log.Type.linkPropertiesRead);
        logRepository.save(log);
        return linkRepository.findLinksByStatusOfThePublicLink(statusOfThePublicLink);
    }
}
