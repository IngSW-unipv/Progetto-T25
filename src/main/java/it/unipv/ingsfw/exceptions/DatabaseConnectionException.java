package it.unipv.ingsfw.exceptions;

/**
 * Eccezione personalizzata per segnalare anomalie o fallimenti
 * durante la connessione al database.
 *
 * @author Leah Appiah
 * @version 1.0
 */
public class DatabaseConnectionException extends RuntimeException {

    public DatabaseConnectionException(String message, Throwable cause) {

        super(message, cause);
    }
}
