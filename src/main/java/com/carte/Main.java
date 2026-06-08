package com.carte;

import com.carte.factory.CardFactory;
import com.carte.repository.CardLoader;
import com.carte.service.*;
import com.carte.facade.UserFacade;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("📦 Avvio sistema collezione carte...");

            CardLoader loader = new CardLoader(new CardFactory());
            CatalogService catalogService = new CatalogService(loader);
            CollectionService collectionService = new CollectionService();
            UserFacade facade = new UserFacade(catalogService, collectionService);
            UserService userService = new UserService();

            ConsoleApp app = new ConsoleApp(facade, userService, collectionService);
            app.run();

        } catch (Exception e) {
            System.err.println("❌ Errore: " + e.getMessage());
        }
    }
}
