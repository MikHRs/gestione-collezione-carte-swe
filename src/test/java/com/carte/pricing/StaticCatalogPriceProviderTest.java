package com.carte.pricing;

import com.carte.entity.Card;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class StaticCatalogPriceProviderTest {

    @Test
    public void testGetPriceReturnsCardCatalogPrice() {
        Card card = new Card(
                1L,
                "Charizard",
                "Base Set",
                "Fuoco",
                new BigDecimal("39.43"),
                1999
        );

        PriceProvider provider = new StaticCatalogPriceProvider();

        assertEquals(new BigDecimal("39.43"), provider.getPrice(card));
    }
}
