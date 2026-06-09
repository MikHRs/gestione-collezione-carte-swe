package com.carte.pricing;

import com.carte.entity.Card;

import java.math.BigDecimal;

public class StaticCatalogPriceProvider implements PriceProvider {

    @Override
    public BigDecimal getPrice(Card card) {
        return card.getPrice();
    }
}
