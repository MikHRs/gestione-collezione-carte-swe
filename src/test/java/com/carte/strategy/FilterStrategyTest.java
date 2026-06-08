package com.carte.strategy;

import com.carte.entity.Card;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class FilterStrategyTest {

    private final List<Card> cards = List.of(
            new Card(1L, "Charizard", "Base Set", "Fuoco", new BigDecimal("39.43"), 1999),
            new Card(2L, "Blastoise", "Base Set", "Acqua", new BigDecimal("60.49"), 1999),
            new Card(3L, "Charmander", "Base Set", "Fuoco", new BigDecimal("44.66"), 1999)
    );

    @Test
    public void filterByTypeReturnsOnlyMatchingCards() {
        FilterByType strategy = new FilterByType("Fuoco");

        List<Card> result = strategy.filter(cards);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(card -> card.getType().equalsIgnoreCase("Fuoco")));
    }

    @Test
    public void filterByNameReturnsCardsContainingKeyword() {
        FilterByName strategy = new FilterByName("char");

        List<Card> result = strategy.filter(cards);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(card -> card.getName().toLowerCase().contains("char")));
    }
}