# GTM - Golf Tee Time Manager

<img src="src/main/resources/view/home_gtm.png" width="500" alt="Schermata Principale GTM" />

**GTM (Golf Tee Time Manager)** è un'applicazione desktop per la gestione, pianificazione e automazione delle prenotazioni dei campi da golf, dei relativi percorsi di gioco (9 o 18 buche) e del noleggio delle attrezzature di supporto (golf cart, sacche di mazze e trolley).



##  1. Prerequisiti di Sistema
Prima di procedere con la compilazione e l'esecuzione del software, assicurati di aver installato e configurato sul tuo computer locale:
* **Java Development Kit (JDK):** Versione 23 o superiore.
* **Apache Maven:** Versione 3.8 o superiore.
* **Database:** MySQL 8.0 o superiore.




##  2. Inizializzazione del Database
1. Accedi alla tua istanza locale di MySQL ed esegui il comando per creare lo schema vuoto di sistema:

   CREATE DATABASE gtm_db;

2. Apri ed esegui l'intero script SQL presente in `docs/3_progettazione/reservation.sql`



##  3. Configurazione dell'Ambiente (Properties)

1. Spostati all'interno della cartella delle risorse del codice sorgente: `src/main/resources/`.
2. Individua il file modello preconfigurato `config.properties.esempio`.
3. Effettua una copia di questo file nella stessa cartella e rinominala esattamente in `config.properties`.
4. Apri il file appena creato con un editor di testo e sostituisci i valori fittizi con i tuoi parametri di rete locali e la password desiderata per l'accesso admin:




## 4. Compilazione ed Esecuzione tramite Maven
Apri il terminale o il prompt dei comandi nella directory radice del progetto ed esegui i seguenti comandi:

* **Esecuzione di Test Unitari (JUnit 5):**
  mvn clean test

* **Avvio dell'applicazione grafica JavaFX:**
  mvn javafx:run
