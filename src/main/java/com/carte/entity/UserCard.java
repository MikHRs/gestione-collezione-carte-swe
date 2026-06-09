package com.carte.entity;

public class UserCard {

    private Card card;
    private String note;
    private int quantity;
    private String condition;

    public UserCard(Card card, String note) {
        this(card, note, 1, "Near Mint");
    }

    public UserCard(Card card, String note, int quantity, String condition) {
        this.card = card;
        this.note = note;
        this.quantity = quantity;
        this.condition = condition;
    }

    public Card getCard() {
        return card;
    }

    public String getNote() {
        return note;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
