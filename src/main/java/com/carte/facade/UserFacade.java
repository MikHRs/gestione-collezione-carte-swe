package com.carte.facade;

import com.carte.entity.*;
import com.carte.service.CatalogService;
import com.carte.service.CollectionService;
import com.carte.strategy.FilterStrategy;

import java.math.BigDecimal;
import java.util.List;

public class UserFacade {

    private final CatalogService catalogService;
    private final CollectionService collectionService;

    public UserFacade(CatalogService catalogService, CollectionService collectionService) {
        this.catalogService = catalogService;
        this.collectionService = collectionService;
    }

    public List<Card> getCatalog() {
        return catalogService.getAllCards();
    }

    public List<Card> filterCatalog(FilterStrategy strategy) {
        return catalogService.filterCards(strategy);
    }

    public void aggiungiCarta(User user, Card card, Album album, String note) {
        collectionService.addCardToAlbum(user, card, album, note);
    }

    public BigDecimal mostraValoreCollezione(User user) {
        return collectionService.getCollectionValue(user);
    }
}
