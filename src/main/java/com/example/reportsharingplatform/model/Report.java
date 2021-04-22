package com.example.reportsharingplatform.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "report")
@Accessors(chain = true)
public class Report {

    @Id
    @Getter
    @Setter
    private String id;

    @DBRef
    @Getter
    @Setter
    private Link linkProperties;

    @Getter
    @Setter
    private String publicLink;

    @DBRef
    @Getter
    @Setter
    private Account actor;

    @DBRef
    @Getter
    @Setter
    private Reference document;

    @DBRef
    @Getter
    @Setter
    private List<Log> history;

    @Getter
    @Setter
    private Date date;

}
