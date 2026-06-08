package com.carte.entity;

public class UserCard {

    private Card card;
    private String note;

    public UserCard(Card card, String note) {
        this.card = card;
        this.note = note;
    }

    public Card getCard() {
        return card;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
