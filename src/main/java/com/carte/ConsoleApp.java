package com.carte;

import com.carte.entity.*;
import com.carte.facade.UserFacade;
import com.carte.service.CollectionService;
import com.carte.service.UserService;
import com.carte.strategy.FilterByType;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private final UserFacade facade;
    private final UserService userService;
    private final CollectionService collectionService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleApp(UserFacade facade, UserService userService, CollectionService collectionService) {
        this.facade = facade;
        this.userService = userService;
        this.collectionService = collectionService;
    }

    public void run() {
        System.out.print("👤 Inserisci username: ");
        String username = scanner.nextLine();
        System.out.print("🔑 Inserisci password: ");
        String password = scanner.nextLine();

        User user = userService.login(username, password);
        if (user == null) {
            System.out.println("❌ Login fallito.");
            return;
        }

        System.out.println("✅ Login riuscito. Benvenuto, " + user.getUsername());
        boolean continua = true;

        while (continua) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Visualizza catalogo");
            System.out.println("2. Aggiungi carta ad album");
            System.out.println("3. Rimuovi carta da album");
            System.out.println("4. Crea nuovo album");
            System.out.println("5. Mostra valore collezione");
            System.out.println("6. Esci");
            System.out.print("Scelta: ");

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1" -> mostraCatalogo();
                case "2" -> aggiungiCarta(user);
                case "3" -> rimuoviCarta(user);
                case "4" -> creaAlbum(user);
                case "5" -> mostraValore(user);
                case "6" -> continua = false;
                default -> System.out.println("Scelta non valida.");
            }
        }

        scanner.close();
    }

    private void mostraCatalogo() {
        System.out.print("Filtro per tipo? (sì/no): ");
        if (scanner.nextLine().equalsIgnoreCase("sì")) {
            System.out.print("Tipo: ");
            String tipo = scanner.nextLine();
            facade.filterCatalog(new FilterByType(tipo)).forEach(System.out::println);
        } else {
            facade.getCatalog().forEach(System.out::println);
        }
    }

    private void aggiungiCarta(User user) {
        List<Card> cards = facade.getCatalog();
        for (int i = 0; i < cards.size(); i++) {
            System.out.println((i + 1) + ". " + cards.get(i));
        }

        System.out.print("Scegli numero carta: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        Card sceltaCarta = cards.get(idx);

        System.out.print("Nome album: ");
        String nomeAlbum = scanner.nextLine();
        Album album = user.getCollection().getAlbum(nomeAlbum);
        if (album == null) {
            album = new Album(nomeAlbum);
            user.getCollection().addAlbum(album);
        }

        System.out.print("Nota: ");
        String nota = scanner.nextLine();
        facade.aggiungiCarta(user, sceltaCarta, album, nota);
    }

    private void rimuoviCarta(User user) {
        System.out.print("Nome album: ");
        String nome = scanner.nextLine();
        Album album = user.getCollection().getAlbum(nome);
        if (album != null) {
            System.out.print("Nome carta: ");
            String nomeCarta = scanner.nextLine();
            Card daRimuovere = album.getCards().stream()
                    .map(UserCard::getCard)
                    .filter(c -> c.getName().equalsIgnoreCase(nomeCarta))
                    .findFirst().orElse(null);
            if (daRimuovere != null) {
                collectionService.removeCardFromAlbum(user, daRimuovere, album);
                System.out.println("✅ Carta rimossa.");
            } else {
                System.out.println("❌ Carta non trovata.");
            }
        } else {
            System.out.println("❌ Album non trovato.");
        }
    }

    private void creaAlbum(User user) {
        System.out.print("Nome nuovo album: ");
        String nuovo = scanner.nextLine();
        user.getCollection().addAlbum(new Album(nuovo));
        System.out.println("✅ Album creato.");
    }

    private void mostraValore(User user) {
        double val = facade.mostraValoreCollezione(user).doubleValue();
        System.out.println("💰 Valore: " + val + " €");
    }
}
