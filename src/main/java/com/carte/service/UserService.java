package com.carte.service;

import com.carte.entity.User;
import com.carte.entity.Collection;
import com.carte.entity.Album;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    // per ora hardcoded: un utente fittizio
    public User login(String username, String password) {
        User user = new User(1L, "michele", "1234"); // simulato
        if (user.getUsername().equals(username) && user.checkPassword(password)) {
            return user;
        }
        return null;
    }

    public List<Album> getAlbums(User user) {
        return user.getCollection().getAlbums();
    }
}
