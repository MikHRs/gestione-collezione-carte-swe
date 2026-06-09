package com.carte.service;

import com.carte.entity.Album;
import com.carte.entity.Card;
import com.carte.entity.User;
import com.carte.entity.UserCard;

public class CollectionService {

    public void addCardToAlbum(User user, Card card, Album album, String note) {
        UserCard uc = new UserCard(card, note);
        album.addCard(uc);
    }

    public void removeCardFromAlbum(User user, Card card, Album album) {
        album.removeCard(card);
    }
}