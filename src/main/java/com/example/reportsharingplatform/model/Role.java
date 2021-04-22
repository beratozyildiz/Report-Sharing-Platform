package com.example.reportsharingplatform.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "role")
@Accessors(chain = true)
public class Role {
    @Id
    @Getter
    @Setter
    private String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)

    private String authorization;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String role) {
        this.authorization = role;
    }


}
