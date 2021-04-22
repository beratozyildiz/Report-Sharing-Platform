package com.example.reportsharingplatform.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "log")
@Accessors(chain = true)
public class Log {
    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private Date date;

    @DBRef
    @Getter
    @Setter
    private Account actor;

    public enum Type {
        accountCreate, accountUpdate, accountDelete, accountRead,
        reportCreate, reportUpdate, reportDelete, reportRead,
        actionRead,
        linkPropertiesCreate, linkPropertiesUpdate, linkPropertiesDelete, linkPropertiesRead, linkPropertiesActivatedLink, linkPropertiesDeactivatedLink,
        publicLinkCreate, publicLinkUpdate, publicLinkDelete, publicLinkRead, publicLinkDownload,
        roleCreate, roleDelete, roleRead, roleUpdate
    }

    @Getter
    @Setter
    private Type type;

}
