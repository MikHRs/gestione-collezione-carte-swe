package com.carte.entity;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class UserCardTest {

    @Test
    public void testDefaultConstructorSetsQuantityAndCondition() {
        Card card = new Card(
                1L,
                "Charizard",
                "Base Set",
                "Fuoco",
                new BigDecimal("39.43"),
                1999
        );

        UserCard userCard = new UserCard(card, "Carta preferita");

        assertEquals(card, userCard.getCard());
        assertEquals("Carta preferita", userCard.getNote());
        assertEquals(1, userCard.getQuantity());
        assertEquals("Near Mint", userCard.getCondition());
    }

    @Test
    public void testCustomQuantityAndCondition() {
        Card card = new Card(
                2L,
                "Blastoise",
                "Base Set",
                "Acqua",
                new BigDecimal("60.49"),
                1999
        );

        UserCard userCard = new UserCard(card, "Buono stato", 3, "Good");

        assertEquals(3, userCard.getQuantity());
        assertEquals("Good", userCard.getCondition());
    }
}
