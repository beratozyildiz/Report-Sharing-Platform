package com.example.reportsharingplatform.repository;

import com.example.reportsharingplatform.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface LogRepository extends MongoRepository<Log, String> {
    List<Log> findLogsByActorId(String id);

    List<Log> findLogsByDateBefore(Date date);

    List<Log> findLogsByDateAfter(Date date);

    List<Log> findLogsByDateBetween(Date date1, Date date2);

    Log findLogById(String id);
}
