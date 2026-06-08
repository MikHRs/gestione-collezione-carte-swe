package com.carte.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import com.carte.entity.Card;
import com.carte.repository.CardLoader;
import com.carte.strategy.FilterStrategy;

public class CatalogService {

    private List<Card> catalog;

    public CatalogService(CardLoader loader) {
        try {
            this.catalog = loader.loadCardsFromClasspath("cards.json");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load cards from file", e);
        }
    }

    public List<Card> getAllCards() {
        return catalog;
    }

    public List<Card> filterCards(FilterStrategy strategy) {
        return strategy.filter(catalog);
    }
    public Card findCardByName(String name) {
    return catalog.stream()
    .filter(c -> c.getName().equalsIgnoreCase(name))
    .findFirst()
    .orElse(null);
    }
}