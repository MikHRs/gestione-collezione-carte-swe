package com.carte.entity;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Long id;
    private String username;
    private String password;
    private Collection collection;


    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.collection = new Collection();

    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }
public Collection getCollection() {
    return collection;
}

}
