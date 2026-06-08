package com.carte.entity;

import java.math.BigDecimal;

public class Card {

    private Long id;
    private String name;
    private String expansion;
    private String type;
    private BigDecimal price;
    private int year;

    public Card(Long id, String name, String expansion, String type, BigDecimal price, int year) {
        this.id = id;
        this.name = name;
        this.expansion = expansion;
        this.type = type;
        this.price = price;
        this.year = year;
    }

    // Getter (puoi aggiungere setter se ti servono)
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getExpansion() { return expansion; }
    public String getType() { return type; }
    public BigDecimal getPrice() { return price; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return name + " (" + expansion + ", " + type + ", " + year + ") - " + price + "€";
    }
}
