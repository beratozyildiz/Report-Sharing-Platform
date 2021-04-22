package com.example.reportsharingplatform.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "account")
@Accessors(chain = true)
public class Account {
    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String surname;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String email;

    @DBRef
    private Set<Role> role;

    public Set<Role> getRole() {
        return role;
    }
    public void setRole(Set<Role> role) {
        this.role = role;
    }

}
