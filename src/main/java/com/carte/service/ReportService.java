package com.carte.service;

import com.carte.entity.Album;
import com.carte.entity.Card;
import com.carte.entity.User;
import com.carte.entity.UserCard;

import java.io.FileWriter;
import java.io.IOException;

public class ReportService {

    private final CollectionValuationService valuationService;

    public ReportService(CollectionValuationService valuationService) {
        this.valuationService = valuationService;
    }

    public void exportCollectionReport(User user, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Report collezione di " + user.getUsername() + "\n\n");

            for (Album album : user.getCollection().getAlbums()) {
                writer.write("Album: " + album.getName() + "\n");

                for (UserCard userCard : album.getCards()) {
                    Card card = userCard.getCard();

                    writer.write("- " + card.getName()
                        + " | Tipo: " + card.getType()
                        + " | Espansione: " + card.getExpansion()
                        + " | Prezzo: " + card.getPrice() + " euro"
                        + " | Quantità: " + userCard.getQuantity()
                        + " | Condizione: " + userCard.getCondition());

                    if (userCard.getNote() != null && !userCard.getNote().isBlank()) {
                        writer.write(" | Nota: " + userCard.getNote());
                    }

                    writer.write("\n");
                }

                writer.write("Valore album: "
                        + valuationService.getAlbumValue(album)
                        + " euro\n\n");
            }

            writer.write("Carte totali: " + valuationService.getTotalCards(user) + "\n");
            writer.write("Valore medio carta: "
                    + valuationService.getAverageCardValue(user)
                    + " euro\n");
            writer.write("Valore totale collezione: "
                    + valuationService.getCollectionValue(user)
                    + " euro\n");
        }
    }
}
