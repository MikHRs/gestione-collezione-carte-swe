package com.carte.pricing;

import com.carte.entity.Card;

import java.math.BigDecimal;

public interface PriceProvider {
    BigDecimal getPrice(Card card);
}
