package com.carte.view;

import com.carte.entity.Card;

import java.util.List;

public class ConsoleView {

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    public void mostraCarte(List<Card> cards) {
        if (cards.isEmpty()) {
            System.out.println("Nessuna carta trovata.");
        } else {
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }
}
