package com.example.reportsharingplatform.repository;

import com.example.reportsharingplatform.model.Reference;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReferenceRepository extends MongoRepository<Reference, String> {
    Reference findReferenceById(String id);

    Reference findReferenceByName(String name);
}

