package com.carte.factory;

import com.carte.entity.Card;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CardFactoryTest {

    @Test
    public void createCardBuildsCardFromMap() {
        CardFactory factory = new CardFactory();

        Map<String, Object> data = new HashMap<>();
        data.put("id", 1L);
        data.put("name", "Charizard");
        data.put("expansion", "Base Set");
        data.put("type", "Fuoco");
        data.put("price", 39.43);
        data.put("year", 1999);

        Card card = factory.createCard(data);

        assertEquals(Long.valueOf(1L), card.getId());
        assertEquals("Charizard", card.getName());
        assertEquals("Base Set", card.getExpansion());
        assertEquals("Fuoco", card.getType());
        assertEquals(new BigDecimal("39.43"), card.getPrice());
        assertEquals(1999, card.getYear());
    }
}