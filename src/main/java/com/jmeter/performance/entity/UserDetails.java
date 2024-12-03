package com.jmeter.performance.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "userDetails")
public class UserDetails {
    @Id
    private String id;
    private String userName;
    private String password;
    private boolean access;

    public UserDetails(String userName, String password, boolean access) {
        this.userName = userName;
        this.password = password;
        this.access = access;
    }
}
