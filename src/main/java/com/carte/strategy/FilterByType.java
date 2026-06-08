package com.carte.strategy;

import java.util.List;
import java.util.stream.Collectors;
import com.carte.entity.Card;

public class FilterByType implements FilterStrategy {

    private final String type;

    public FilterByType(String type) {
        this.type = type.toLowerCase();
    }

    @Override
    public List<Card> filter(List<Card> cards) {
        return cards.stream()
            .filter(card -> card.getType().equalsIgnoreCase(type))
            .collect(Collectors.toList());
    }
}
