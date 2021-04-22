package com.example.reportsharingplatform.controller;


import com.example.reportsharingplatform.model.Log;
import com.example.reportsharingplatform.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("log")
public class LogController {
    @Autowired
    private LogService logService;

    @PostMapping
    public Log save(@RequestBody Log log) {
        return logService.save(log);
    }

    @GetMapping("list")
    public List<Log> list() {
        return logService.getList();
    }

    @GetMapping("{id}")
    public Log getLog(@PathVariable("id") String id) {
        return logService.getLogById(id);
    }

    @GetMapping("list/before/{date}")
    public List<Log> getLogsBeforeDate(@PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
        return logService.getListBeforeDate(date);
    }

    @GetMapping("list/after/{date}")
    public List<Log> getLogsBeforeAfter(@PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
        return logService.getListAfterDate(date);
    }

    @GetMapping("list/actor/{actorId}")
    public List<Log> getLogsByActorId(@PathVariable("actorId") String actorId) {
        return logService.getListByActor(actorId);
    }


    @GetMapping("list/between/{date1}/{date2}")
    public List<Log> getLogBetweenDates(@PathVariable(value = "date1") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date1,
                                        @PathVariable(value = "date2") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date2) {
        return logService.getListBetweenDates(date1, date2);
    }

}
