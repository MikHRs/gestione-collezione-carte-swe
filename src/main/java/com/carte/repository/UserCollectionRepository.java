package com.carte.repository;

import com.carte.entity.Album;
import com.carte.entity.Card;
import com.carte.entity.User;
import com.carte.entity.UserCard;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class UserCollectionRepository {

    public void saveCollection(User user, String filePath) throws IOException {
        JSONObject root = new JSONObject();

        root.put("username", user.getUsername());

        JSONArray albumsArray = new JSONArray();

        for (Album album : user.getCollection().getAlbums()) {
            JSONObject albumObject = new JSONObject();
            albumObject.put("name", album.getName());

            JSONArray cardsArray = new JSONArray();

            for (UserCard userCard : album.getCards()) {
                Card card = userCard.getCard();

                JSONObject cardObject = new JSONObject();
                cardObject.put("id", card.getId());
                cardObject.put("name", card.getName());
                cardObject.put("expansion", card.getExpansion());
                cardObject.put("type", card.getType());
                cardObject.put("price", card.getPrice());
                cardObject.put("year", card.getYear());
                cardObject.put("note", userCard.getNote());
                cardObject.put("quantity", userCard.getQuantity());
                cardObject.put("condition", userCard.getCondition());

                cardsArray.put(cardObject);
            }

            albumObject.put("cards", cardsArray);
            albumsArray.put(albumObject);
        }

        root.put("albums", albumsArray);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(root.toString(4));
        }
    }
}
