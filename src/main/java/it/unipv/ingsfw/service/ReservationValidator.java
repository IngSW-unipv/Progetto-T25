package it.unipv.ingsfw.service;


import it.unipv.ingsfw.dao.ReservationDao;
import it.unipv.ingsfw.exceptions.InvalidReservationException;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Classe che valida le regole di business per le prenotazioni.
 * Verifica la correttezza della data selezionata, la capacità residua dei tee time
 * e i limiti sul noleggio dell'attrezzatura.
 *
 * @author Leah Appiah
 * @version 1.0
 */
public class ReservationValidator {

    private final ReservationDao dao = new ReservationDao();

    public ReservationValidator() {
    }

    /**
     * Verifica che la data inserita non sia nulla e non appartenga al passato.
     *
     * @param dataSelezionata La data scelta dall'utente.
     * @throws InvalidReservationException Se la data è nulla o precedente a oggi.
     */
    public void validaData(LocalDate dataSelezionata) throws InvalidReservationException {
        if (dataSelezionata == null) {
            throw new InvalidReservationException("Seleziona una data valida dal calendario!");
        }
        if (dataSelezionata.isBefore(LocalDate.now())) {
            throw new InvalidReservationException("Impossibile inserire una prenotazione nel passato! Data scelta: " + dataSelezionata);
        }
    }

    /**
     * Controlla la capacità residua di uno specifico slot orario (max 4 giocatori per tee time).
     *
     * @param data La data della prenotazione.
     * @param orario L'orario di partenza dello slot.
     * @param nuoviGiocatori Il numero di giocatori che si intende prenotare.
     * @throws InvalidReservationException Se i posti liberi non sono sufficienti.
     * @throws SQLException In caso di errore durante la query sul database.
     */
    public void validaCapacitaTeeTime(String data, String orario, int nuoviGiocatori) throws InvalidReservationException, SQLException {
        int postiOccupati = dao.getPostiOccupati(data, orario);
        int postiLiberi = 4 - postiOccupati;

        if (postiLiberi < nuoviGiocatori) {
            throw new InvalidReservationException("Capacità insufficiente per lo slot delle " + orario +
                    ". Posti rimasti: " + postiLiberi + ", richiesti: " + nuoviGiocatori);
        }
    }

    /**
     * Valida la coerenza tra il numero di giocatori e l'attrezzatura a noleggio richiesta.
     * Impedisce che il numero di sacche superi i giocatori o che la somma di cart e trolley sia eccedente.
     *
     * @param numeroGiocatori Numero di giocatori nel gruppo.
     * @param sacche Numero di sacche da golf richieste a noleggio.
     * @param cart Numero di cart richiesti a noleggio.
     * @param trolley Numero di carrelli (trolley) richiesti a noleggio.
     * @throws InvalidReservationException Se i quantitativi violano le regole aziendali.
     */
    public void validaAttrezzatura(int numeroGiocatori, int sacche, int cart, int trolley) throws InvalidReservationException {
        if (numeroGiocatori <= 0) {
            throw new InvalidReservationException("Numero di giocatori non valido!");
        }
        if (sacche > numeroGiocatori) {
            throw new InvalidReservationException("Non puoi selezionare " + sacche + " sacche per un gruppo di " + numeroGiocatori + " giocatori!");
        }
        if ((cart + trolley) > numeroGiocatori) {
            throw new InvalidReservationException("La somma di Cart e Trolley (" + (cart + trolley) +
                    ") non può superare il numero totale di giocatori (" + numeroGiocatori + ")!");
        }
    }
}