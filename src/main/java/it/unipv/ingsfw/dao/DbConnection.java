package it.unipv.ingsfw.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import it.unipv.ingsfw.exceptions.DatabaseConnectionException;

/**
 * Classe per la connessione al database
 */

public class DbConnection {

    private static DbConnection instance = null; //pattern singleton per la connesione al databse, unica istanza
    private Connection connection = null;
    private String url;
    private String user;
    private String password;

    private DbConnection() {

        Properties prop = new Properties(); //per file config.properties con le credenziali

        try (InputStream input = DbConnection.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("Impossibile trovare il file config.properties!");
            }
            prop.load(input);
            this.url = prop.getProperty("gtm.db.url");
            this.user = prop.getProperty("gtm.db.user");
            this.password = prop.getProperty("gtm.db.password");

        } catch (Exception e) {
            throw new DatabaseConnectionException("Errore nel caricamento della configurazione dal file properties!", e);
        }
    }

    //syncronized per il thread-safe del singleton
    public static synchronized DbConnection getInstance() {

        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(url, user, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseConnectionException("Errore di connessione al DB MySQL!", e);
        }

        return connection;
    }
}
