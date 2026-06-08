package com.carte.service;

import com.carte.entity.Album;
import com.carte.entity.Card;
import com.carte.entity.User;
import com.carte.entity.UserCard;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionServiceTest {

    @Test
    public void addCardToAlbumAddsUserCard() {
        CollectionService service = new CollectionService();

        User user = new User(1L, "michele", "1234");
        Album album = new Album("Base Set");
        Card card = new Card(1L, "Charizard", "Base Set", "Fuoco", new BigDecimal("39.43"), 1999);

        service.addCardToAlbum(user, card, album, "Carta preferita");

        assertEquals(1, album.getCards().size());
        assertEquals("Charizard", album.getCards().get(0).getCard().getName());
        assertEquals("Carta preferita", album.getCards().get(0).getNote());
    }

    @Test
    public void removeCardFromAlbumRemovesUserCard() {
        CollectionService service = new CollectionService();

        User user = new User(1L, "michele", "1234");
        Album album = new Album("Base Set");
        Card card = new Card(1L, "Charizard", "Base Set", "Fuoco", new BigDecimal("39.43"), 1999);

        service.addCardToAlbum(user, card, album, "Da rimuovere");
        assertEquals(1, album.getCards().size());

        service.removeCardFromAlbum(user, card, album);

        assertTrue(album.getCards().isEmpty());
    }

    @Test
    public void getCollectionValueSumsAllCardsInAlbums() {
        CollectionService service = new CollectionService();

        User user = new User(1L, "michele", "1234");
        Album album = new Album("Preferite");

        Card charizard = new Card(1L, "Charizard", "Base Set", "Fuoco", new BigDecimal("39.43"), 1999);
        Card blastoise = new Card(2L, "Blastoise", "Base Set", "Acqua", new BigDecimal("60.49"), 1999);

        user.getCollection().addAlbum(album);

        service.addCardToAlbum(user, charizard, album, "");
        service.addCardToAlbum(user, blastoise, album, "");

        assertEquals(new BigDecimal("99.92"), service.getCollectionValue(user));
    }
    @Test
public void getAlbumValueReturnsSumOfCardsInAlbum() {
    CollectionService service = new CollectionService();

    Album album = new Album("Base Set");
    Card charizard = new Card(1L, "Charizard", "Base Set", "Fuoco", new BigDecimal("39.43"), 1999);
    Card blastoise = new Card(2L, "Blastoise", "Base Set", "Acqua", new BigDecimal("60.49"), 1999);

    album.addCard(new UserCard(charizard, ""));
    album.addCard(new UserCard(blastoise, ""));

    assertEquals(new BigDecimal("99.92"), service.getAlbumValue(album));
}

@Test
public void getAverageCardValueReturnsAverageValue() {
    CollectionService service = new CollectionService();

    User user = new User(1L, "michele", "1234");
    Album album = new Album("Preferite");

    Card charizard = new Card(1L, "Charizard", "Base Set", "Fuoco", new BigDecimal("40.00"), 1999);
    Card blastoise = new Card(2L, "Blastoise", "Base Set", "Acqua", new BigDecimal("60.00"), 1999);

    user.getCollection().addAlbum(album);
    album.addCard(new UserCard(charizard, ""));
    album.addCard(new UserCard(blastoise, ""));

    assertEquals(new BigDecimal("50.00"), service.getAverageCardValue(user));
}

@Test
public void getMostValuableCardsReturnsCardsOrderedByPrice() {
    CollectionService service = new CollectionService();

    User user = new User(1L, "michele", "1234");
    Album album = new Album("Preferite");

    Card charizard = new Card(1L, "Charizard", "Base Set", "Fuoco", new BigDecimal("40.00"), 1999);
    Card blastoise = new Card(2L, "Blastoise", "Base Set", "Acqua", new BigDecimal("60.00"), 1999);
    Card pikachu = new Card(3L, "Pikachu", "Base Set", "Elettro", new BigDecimal("20.00"), 1999);

    user.getCollection().addAlbum(album);
    album.addCard(new UserCard(charizard, ""));
    album.addCard(new UserCard(blastoise, ""));
    album.addCard(new UserCard(pikachu, ""));

    List<UserCard> result = service.getMostValuableCards(user, 2);

    assertEquals(2, result.size());
    assertEquals("Blastoise", result.get(0).getCard().getName());
    assertEquals("Charizard", result.get(1).getCard().getName());
}
}