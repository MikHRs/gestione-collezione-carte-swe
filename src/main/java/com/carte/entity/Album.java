package com.carte.entity;

import java.util.ArrayList;
import java.util.List;

public class Album {

    private Long id;
    private String name;
    private List<UserCard> cards = new ArrayList<>();

    public Album(String name) {
        this.id = System.currentTimeMillis(); // o UUID
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * ✅ Defensive copy in uscita
     */
    public List<UserCard> getCards() {
        return new ArrayList<>(cards);
    }

    public void addCard(UserCard userCard) {
        cards.add(userCard);
    }

    public void setCards(List<UserCard> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void removeCard(Card card) {
    cards.removeIf(userCard ->
        userCard.getCard().getName().equalsIgnoreCase(card.getName())
    );
    }
}
