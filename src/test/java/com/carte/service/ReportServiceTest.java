package com.carte.service;

import com.carte.entity.Album;
import com.carte.entity.Card;
import com.carte.entity.User;
import com.carte.entity.UserCard;
import com.carte.pricing.StaticCatalogPriceProvider;

import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class ReportServiceTest {

    @Test
    public void testExportCollectionReportCreatesReadableReport() throws Exception {
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

        CollectionValuationService valuationService =
                new CollectionValuationService(new StaticCatalogPriceProvider());

        ReportService reportService = new ReportService(valuationService);

        File tempFile = File.createTempFile("collection-report", ".txt");
        tempFile.deleteOnExit();

        reportService.exportCollectionReport(user, tempFile.getAbsolutePath());

        String content = Files.readString(tempFile.toPath());

        assertTrue(content.contains("Report collezione di michele"));
        assertTrue(content.contains("Album: Preferite"));
        assertTrue(content.contains("Charizard"));
        assertTrue(content.contains("Quantità: 2"));
        assertTrue(content.contains("Condizione: Near Mint"));
        assertTrue(content.contains("Valore totale collezione"));
    }
}
