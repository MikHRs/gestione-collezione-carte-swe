package com.carte.service;
import java.io.FileWriter;
import java.io.IOException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

import com.carte.entity.Album;
import com.carte.entity.Card;
import com.carte.entity.User;
import com.carte.entity.UserCard;

public class CollectionService {

    public void addCardToAlbum(User user, Card card, Album album, String note) {
        UserCard uc = new UserCard(card, note);
        album.addCard(uc);
    }
    
    public void removeCardFromAlbum(User user, Card card, Album album) {
        album.removeCard(card);
    }


    public BigDecimal getCollectionValue(User user) {
        return user.getCollection().getAlbums().stream()
            .flatMap(album -> album.getCards().stream())
            .map(userCard -> userCard.getCard().getPrice())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getAlbumValue(Album album) {
    return album.getCards().stream()
            .map(userCard -> userCard.getCard().getPrice())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
}

public int getTotalCards(User user) {
    return user.getCollection().getAlbums().stream()
            .mapToInt(album -> album.getCards().size())
            .sum();
}

public BigDecimal getAverageCardValue(User user) {
    int totalCards = getTotalCards(user);

    if (totalCards == 0) {
        return BigDecimal.ZERO;
    }

    return getCollectionValue(user)
            .divide(BigDecimal.valueOf(totalCards), 2, RoundingMode.HALF_UP);
}

public List<UserCard> getMostValuableCards(User user, int limit) {
    return user.getCollection().getAlbums().stream()
            .flatMap(album -> album.getCards().stream())
            .sorted(Comparator.comparing(
                    (UserCard userCard) -> userCard.getCard().getPrice()
            ).reversed())
            .limit(limit)
            .toList();
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
                        + " | Prezzo: " + card.getPrice() + " euro");

                if (userCard.getNote() != null && !userCard.getNote().isBlank()) {
                    writer.write(" | Nota: " + userCard.getNote());
                }

                writer.write("\n");
            }

            writer.write("Valore album: " + getAlbumValue(album) + " euro\n\n");
        }

        writer.write("Carte totali: " + getTotalCards(user) + "\n");
        writer.write("Valore medio carta: " + getAverageCardValue(user) + " euro\n");
        writer.write("Valore totale collezione: " + getCollectionValue(user) + " euro\n");
    }
}
    
}

