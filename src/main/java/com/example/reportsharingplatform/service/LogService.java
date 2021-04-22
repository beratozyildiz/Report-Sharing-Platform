package com.example.reportsharingplatform.service;

import com.example.reportsharingplatform.model.Account;
import com.example.reportsharingplatform.model.Log;
import com.example.reportsharingplatform.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public Log save(Log log) {
        return logRepository.save(log);
    }

    public Log tempLog(Account account){
        Log log = new Log()
                .setId(UUID.randomUUID().toString())
                .setDate(new Date())
                .setActor(account)
                .setType(null);
        return log;
    }

    public List<Log> getList() {
        return logRepository.findAll();
    }

    public Log getLogById(String logId) {
        return logRepository.findLogById(logId);
    }

    public List<Log> getListBeforeDate(Date date) {
        return logRepository.findLogsByDateBefore(date);
    }

    public List<Log> getListAfterDate(Date date) {
        return logRepository.findLogsByDateAfter(date);
    }

    public List<Log> getListBetweenDates(Date date1, Date date2) {
        return logRepository.findLogsByDateBetween(date1, date2);
    }

    public List<Log> getListByActor(String actorId) {
        return logRepository.findLogsByActorId(actorId);
    }
}
