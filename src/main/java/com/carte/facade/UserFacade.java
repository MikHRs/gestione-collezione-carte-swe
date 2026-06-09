package com.carte.facade;

import com.carte.entity.Album;
import com.carte.entity.Card;
import com.carte.entity.User;
import com.carte.pricing.StaticCatalogPriceProvider;
import com.carte.service.CatalogService;
import com.carte.service.CollectionService;
import com.carte.service.CollectionValuationService;
import com.carte.strategy.FilterStrategy;

import java.math.BigDecimal;
import java.util.List;

public class UserFacade {

    private final CatalogService catalogService;
    private final CollectionService collectionService;
    private final CollectionValuationService valuationService;

    public UserFacade(CatalogService catalogService, CollectionService collectionService) {
        this.catalogService = catalogService;
        this.collectionService = collectionService;
        this.valuationService = new CollectionValuationService(new StaticCatalogPriceProvider());
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

    public void rimuoviCarta(User user, Card card, Album album) {
        collectionService.removeCardFromAlbum(user, card, album);
    }

    public BigDecimal mostraValoreCollezione(User user) {
        return valuationService.getCollectionValue(user);
    }

    public BigDecimal mostraValoreAlbum(Album album) {
        return valuationService.getAlbumValue(album);
    }
}