package com.carte.factory;

import com.carte.entity.Card;

import java.math.BigDecimal;
import java.util.Map;

public class CardFactory {

    public Card createCard(Map<String, Object> data) {
        Long id = ((Number) data.get("id")).longValue();
        String name = (String) data.get("name");
        String expansion = (String) data.get("expansion");
        String type = (String) data.get("type");
        BigDecimal price = new BigDecimal(data.get("price").toString());
        int year = ((Number) data.get("year")).intValue();

        return new Card(id, name, expansion, type, price, year);
    }
}
