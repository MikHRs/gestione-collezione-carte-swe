# Gestione Collezione Carte Collezionabili

Progetto sviluppato per l'esame di Ingegneria del Software.

L'applicazione consente di gestire una collezione personale di carte collezionabili, organizzandole in album e calcolando il valore economico della collezione sulla base dei prezzi presenti nel catalogo.

## Funzionalità principali

- Caricamento del catalogo carte da file JSON
- Visualizzazione del catalogo
- Filtri per nome e tipo tramite Strategy Pattern
- Gestione di album personali
- Aggiunta e rimozione di carte dagli album
- Calcolo del valore totale della collezione
- Calcolo del valore del singolo album
- Individuazione delle carte di maggior valore
- Esportazione di un report testuale della collezione

## Tecnologie utilizzate

- Java 17
- Maven
- JUnit
- JSON
- PlantUML / LaTeX per la documentazione

## Esecuzione

Per compilare il progetto:

```bash
mvn clean compile
Per eseguire i test:
mvn clean test

Per avviare l'applicazione:

mvn exec:java
