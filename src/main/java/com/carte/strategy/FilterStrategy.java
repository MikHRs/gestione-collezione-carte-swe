package com.carte.strategy;

import java.util.List;

import com.carte.entity.Card;

public interface FilterStrategy {
    List<Card> filter(List<Card> cards);
}
