package com.carte.service;

import com.carte.factory.CardFactory;
import com.carte.repository.CardLoader;
import com.carte.strategy.FilterByType;
import org.junit.Test;

import static org.junit.Assert.*;

public class CatalogServiceTest {

    @Test
    public void catalogServiceLoadsCardsFromJson() {
        CatalogService service = new CatalogService(new CardLoader(new CardFactory()));

        assertFalse(service.getAllCards().isEmpty());
        assertNotNull(service.findCardByName("Charizard"));
    }

    @Test
    public void catalogServiceFiltersCardsByType() {
        CatalogService service = new CatalogService(new CardLoader(new CardFactory()));

        assertFalse(service.filterCards(new FilterByType("Fuoco")).isEmpty());
        assertTrue(service.filterCards(new FilterByType("Fuoco"))
                .stream()
                .allMatch(card -> card.getType().equalsIgnoreCase("Fuoco")));
    }
}