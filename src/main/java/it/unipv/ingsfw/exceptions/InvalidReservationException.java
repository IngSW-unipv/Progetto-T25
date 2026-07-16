package it.unipv.ingsfw.exceptions;

/**
 * Eccezione personalizzata per segnalare violazioni dei vincoli di business
 * o di validazione durante la creazione di una prenotazione.
 *
 * @author Leah Appiah
 * @version 1.0
 */
public class InvalidReservationException extends Exception {

    public InvalidReservationException(String message) {
        super(message);
    }
}