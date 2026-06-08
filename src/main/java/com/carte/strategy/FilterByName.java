package com.carte.strategy;

import java.util.List;
import java.util.stream.Collectors;

import com.carte.entity.Card;

public class FilterByName implements FilterStrategy {

    private String keyword;

    public FilterByName(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public List<Card> filter(List<Card> cards) {
        return cards.stream()
            .filter(card -> card.getName().toLowerCase().contains(keyword))
            .collect(Collectors.toList());
    }
}
