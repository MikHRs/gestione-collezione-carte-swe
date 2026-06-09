package com.carte.service;

import com.carte.entity.Album;
import com.carte.entity.User;
import com.carte.entity.UserCard;
import com.carte.pricing.PriceProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

public class CollectionValuationService {

    private final PriceProvider priceProvider;

    public CollectionValuationService(PriceProvider priceProvider) {
        this.priceProvider = priceProvider;
    }

   public BigDecimal getAlbumValue(Album album) {
    return album.getCards().stream()
            .map(userCard -> priceProvider.getPrice(userCard.getCard())
                    .multiply(BigDecimal.valueOf(userCard.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
}

    public BigDecimal getCollectionValue(User user) {
        return user.getCollection().getAlbums().stream()
                .map(this::getAlbumValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

   public int getTotalCards(User user) {
    return user.getCollection().getAlbums().stream()
            .flatMap(album -> album.getCards().stream())
            .mapToInt(UserCard::getQuantity)
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
                        userCard -> priceProvider.getPrice(userCard.getCard()),
                        Comparator.reverseOrder()
                ))
                .limit(limit)
                .toList();
    }
}
