package com.example.reportsharingplatform.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reference")
@Accessors(chain = true)
public class Reference {
    @Id
    @Getter
    @Setter
    private String id;

    public enum Type {pdf, doc, zip}

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Type type;

    @Getter
    @Setter
    private String path;

    @Getter
    @Setter
    private String key;

    @Getter
    @Setter
    private long size;

}
