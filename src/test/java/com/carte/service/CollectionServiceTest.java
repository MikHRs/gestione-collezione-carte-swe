package com.carte.service;

import com.carte.entity.Album;
import com.carte.entity.Card;
import com.carte.entity.User;
import com.carte.entity.UserCard;
import com.carte.pricing.StaticCatalogPriceProvider;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionServiceTest {

    private CollectionService collectionService;
    private CollectionValuationService valuationService;
    private User user;
    private Album album;
    private Card charizard;
    private Card blastoise;

    @Before
    public void setUp() {
        collectionService = new CollectionService();
        valuationService = new CollectionValuationService(new StaticCatalogPriceProvider());

        user = new User(1L, "michele", "password");
        album = new Album("Preferite");

        user.getCollection().addAlbum(album);

        charizard = new Card(
                1L,
                "Charizard",
                "Base Set",
                "Fuoco",
                new BigDecimal("39.43"),
                1999
        );

        blastoise = new Card(
                2L,
                "Blastoise",
                "Base Set",
                "Acqua",
                new BigDecimal("60.49"),
                1999
        );
    }

    @Test
    public void testAddCardToAlbum() {
        collectionService.addCardToAlbum(user, charizard, album, "Carta preferita");

        assertEquals(1, album.getCards().size());
        assertEquals("Charizard", album.getCards().get(0).getCard().getName());
        assertEquals("Carta preferita", album.getCards().get(0).getNote());
    }

    @Test
    public void testRemoveCardFromAlbum() {
        collectionService.addCardToAlbum(user, charizard, album, "Da rimuovere");

        collectionService.removeCardFromAlbum(user, charizard, album);

        assertEquals(0, album.getCards().size());
    }

    @Test
    public void testGetCollectionValue() {
        collectionService.addCardToAlbum(user, charizard, album, "");
        collectionService.addCardToAlbum(user, blastoise, album, "");

        BigDecimal value = valuationService.getCollectionValue(user);

        assertEquals(new BigDecimal("99.92"), value);
    }

    @Test
    public void testGetAlbumValue() {
        collectionService.addCardToAlbum(user, charizard, album, "");
        collectionService.addCardToAlbum(user, blastoise, album, "");

        BigDecimal value = valuationService.getAlbumValue(album);

        assertEquals(new BigDecimal("99.92"), value);
    }

    @Test
    public void testGetTotalCards() {
        collectionService.addCardToAlbum(user, charizard, album, "");
        collectionService.addCardToAlbum(user, blastoise, album, "");

        int total = valuationService.getTotalCards(user);

        assertEquals(2, total);
    }

    @Test
    public void testGetAverageCardValue() {
        collectionService.addCardToAlbum(user, charizard, album, "");
        collectionService.addCardToAlbum(user, blastoise, album, "");

        BigDecimal average = valuationService.getAverageCardValue(user);

        assertEquals(new BigDecimal("49.96"), average);
    }

    @Test
    public void testGetAverageCardValueWithEmptyCollection() {
        BigDecimal average = valuationService.getAverageCardValue(user);

        assertEquals(BigDecimal.ZERO, average);
    }

    @Test
    public void testGetMostValuableCards() {
        collectionService.addCardToAlbum(user, charizard, album, "");
        collectionService.addCardToAlbum(user, blastoise, album, "");

        List<UserCard> mostValuable = valuationService.getMostValuableCards(user, 1);

        assertEquals(1, mostValuable.size());
        assertEquals("Blastoise", mostValuable.get(0).getCard().getName());
    }
}