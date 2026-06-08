package com.carte.controller;

import com.carte.entity.*;
import com.carte.facade.UserFacade;

public class UserController {

    private final User user;
    private final UserFacade facade;

    public UserController(User user, UserFacade facade) {
        this.user = user;
        this.facade = facade;
    }

    public void aggiungiCarta(Card card, String nomeAlbum, String note) {
        Album album = user.getCollection().getAlbums().stream()
                        .filter(a -> a.getName().equalsIgnoreCase(nomeAlbum))
                        .findFirst()
                        .orElse(null);
        if (album != null) {
            facade.aggiungiCarta(user, card, album, note);
            System.out.println("✅ Carta aggiunta all'album " + nomeAlbum);
        } else {
            System.out.println("❌ Album non trovato.");
        }
    }

    public void mostraValoreCollezione() {
        System.out.println("Valore totale: " + facade.mostraValoreCollezione(user) + " €");
    }
}
