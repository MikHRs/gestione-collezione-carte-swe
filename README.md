# Sistema Java per la Gestione e Valutazione di una Collezione di Carte

Progetto realizzato per l'esame di Ingegneria del Software.

L'applicazione permette di gestire una collezione personale di carte collezionabili. Il sistema consente di visualizzare un catalogo di carte caricato da file JSON, filtrare le carte, organizzarle in album personali, aggiungere note, quantità e condizione delle carte possedute, calcolare il valore economico della collezione ed esportare report.

## Funzionalità principali

* caricamento del catalogo carte da file JSON;
* visualizzazione del catalogo;
* ricerca e filtro delle carte;
* gestione di album personali;
* aggiunta e rimozione di carte dagli album;
* gestione di note personali sulle carte;
* gestione di quantità e condizione della carta posseduta;
* calcolo del valore totale della collezione;
* calcolo del valore di un singolo album;
* calcolo del valore medio delle carte;
* visualizzazione delle carte di maggior valore;
* esportazione di un report testuale;
* salvataggio della collezione utente in formato JSON.

## Tecnologie utilizzate

* Java 17
* Maven
* JUnit
* JSON
* PlantUML
* Git / GitHub

## Struttura del progetto

```text
src/main/java/com/carte
├── controller
├── entity
├── facade
├── factory
├── pricing
├── repository
├── service
├── strategy
└── view
```

## Architettura

Il progetto segue una struttura a livelli ispirata al pattern MVC.

* `entity`: contiene le classi del dominio, come `Card`, `UserCard`, `Album`, `Collection` e `User`;
* `service`: contiene la logica applicativa;
* `repository`: gestisce il caricamento e il salvataggio dei dati;
* `factory`: contiene la logica di creazione degli oggetti `Card`;
* `strategy`: contiene le strategie di filtro del catalogo;
* `facade`: espone un'interfaccia semplificata verso i servizi;
* `view` e `controller`: gestiscono l'interazione con l'utente.

## Design pattern utilizzati

### Factory

La classe `CardFactory` centralizza la creazione degli oggetti `Card` a partire dai dati letti dal catalogo JSON.

### Strategy

L'interfaccia `FilterStrategy` permette di definire strategie diverse di filtro del catalogo, ad esempio per nome o per tipo.

### Facade

La classe `UserFacade` fornisce un punto di accesso semplificato alle funzionalità principali del sistema.

### Price Provider

L'interfaccia `PriceProvider` astrae il recupero del prezzo di una carta.
L'implementazione attuale, `StaticCatalogPriceProvider`, usa il prezzo presente nel catalogo JSON. In futuro il sistema potrebbe introdurre un provider basato su API esterne.

## Valutazione economica

La logica di valutazione economica è separata nella classe `CollectionValuationService`.

Questa classe permette di calcolare:

* valore totale della collezione;
* valore di un album;
* numero totale di carte possedute;
* valore medio delle carte;
* carte di maggior valore.

Il valore tiene conto anche della quantità posseduta di ciascuna carta.

## Persistenza

Il catalogo delle carte viene caricato da un file JSON.

Inoltre, la classe `UserCollectionRepository` permette di salvare la collezione personale dell'utente in formato JSON, includendo album, carte, note, quantità e condizione.

## Test

Il progetto include test JUnit per verificare le principali funzionalità del sistema.

Per eseguire i test:

```bash
mvn clean test
```

## Compilazione

Per compilare il progetto:

```bash
mvn clean compile
```

## Esecuzione

Per eseguire l'applicazione:

```bash
mvn exec:java
```

## Possibili sviluppi futuri

* integrazione con API esterne per aggiornare i prezzi delle carte;
* interfaccia grafica;
* persistenza completa con database;
* gestione di utenti multipli;
* ordinamento e statistiche avanzate sulla collezione.

