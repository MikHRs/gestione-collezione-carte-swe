package com.carte.repository;

import com.carte.entity.Card;
import com.carte.factory.CardFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CardLoader {

    private final CardFactory cardFactory;

    public CardLoader(CardFactory cardFactory) {
        this.cardFactory = cardFactory;
    }

    public List<Card> loadCardsFromClasspath(String fileName) throws IOException {
        List<Card> cards = new ArrayList<>();

        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            throw new FileNotFoundException("File non trovato nel classpath: " + fileName);
        }

        try {
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            JSONArray array = new JSONArray(json);

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                if (!obj.has("id") || !obj.has("name") || !obj.has("expansion") ||
                    !obj.has("type") || !obj.has("price") || !obj.has("year")) {
                    throw new IllegalArgumentException("JSON mancante campi richiesti alla posizione " + i);
                }

                Map<String, Object> data = new HashMap<>();
                data.put("id", obj.getLong("id"));
                data.put("name", obj.getString("name"));
                data.put("expansion", obj.getString("expansion"));
                data.put("type", obj.getString("type"));
                data.put("price", obj.getDouble("price"));
                data.put("year", obj.getInt("year"));

                cards.add(cardFactory.createCard(data));
            }

        } catch (IOException e) {
            throw new IOException("Errore durante la lettura del file JSON: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Errore durante il parsing del JSON: " + e.getMessage(), e);
        }

        return cards;
    }
}
