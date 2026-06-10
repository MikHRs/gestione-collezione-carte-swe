package com.carte.repository;

import com.carte.entity.Album;
import com.carte.entity.Card;
import com.carte.entity.User;
import com.carte.entity.UserCard;

import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class UserCollectionRepositoryTest {

    @Test
    public void testSaveCollectionCreatesJsonFile() throws Exception {
        User user = new User(1L, "michele", "password");
        Album album = new Album("Preferite");

        Card card = new Card(
                1L,
                "Charizard",
                "Base Set",
                "Fuoco",
                new BigDecimal("39.43"),
                1999
        );

        UserCard userCard = new UserCard(card, "Carta preferita", 2, "Near Mint");

        album.addCard(userCard);
        user.getCollection().addAlbum(album);

        File tempFile = File.createTempFile("collection-test", ".json");
        tempFile.deleteOnExit();

        UserCollectionRepository repository = new UserCollectionRepository();
        repository.saveCollection(user, tempFile.getAbsolutePath());

        String content = Files.readString(tempFile.toPath());

        assertTrue(content.contains("michele"));
        assertTrue(content.contains("Preferite"));
        assertTrue(content.contains("Charizard"));
        assertTrue(content.contains("Near Mint"));
        assertTrue(content.contains("\"quantity\": 2"));
    }
}
