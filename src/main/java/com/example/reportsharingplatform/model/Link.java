package com.example.reportsharingplatform.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "link")
@Accessors(chain = true)
public class Link {
    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private boolean reusabilityOfTheLink;


    public enum LinkPermission {read, write}

    @Setter
    @Getter
    private LinkPermission linkPermission;

    @Getter
    @Setter
    private boolean statusOfThePublicLink;

}
