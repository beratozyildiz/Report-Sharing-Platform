package com.example.reportsharingplatform.repository;

import com.example.reportsharingplatform.model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LinkRepository extends MongoRepository<Link, String> {
    Link findLinkById(String id);

    List<Link> findLinksByReusabilityOfTheLink(boolean reusabilityOfTheLink);

    List<Link> findLinksByStatusOfThePublicLink(boolean statusOfThePublicLink);
}
