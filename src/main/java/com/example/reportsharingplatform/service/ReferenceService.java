package com.example.reportsharingplatform.service;

import com.example.reportsharingplatform.model.Reference;
import com.example.reportsharingplatform.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReferenceService {
    @Autowired
    private ReferenceRepository referenceRepository;

    public List<Reference> getList() {
        return referenceRepository.findAll();
    }

    public void delete(String referenceId) {
        Reference reference = referenceRepository.findReferenceById(referenceId);
        referenceRepository.delete(reference);
    }
    public Reference getReference(String referenceId) {
        return referenceRepository.findReferenceById(referenceId);
    }

    public Reference getReferenceByName(String name) {
        return referenceRepository.findReferenceByName(name);
    }
}
