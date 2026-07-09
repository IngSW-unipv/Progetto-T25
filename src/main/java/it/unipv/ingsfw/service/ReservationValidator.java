package it.unipv.ingsfw.service;


import it.unipv.ingsfw.dao.ReservationDao;
import it.unipv.ingsfw.exceptions.InvalidReservationException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReservationValidator {

    private final ReservationDao dao = new ReservationDao();

    public ReservationValidator() {
    }
    public void validaData(LocalDate dataSelezionata) throws InvalidReservationException {
        if (dataSelezionata == null) {
            throw new InvalidReservationException("Seleziona una data valida dal calendario!");
        }
        if (dataSelezionata.isBefore(LocalDate.now())) {
            throw new InvalidReservationException("Impossibile inserire una prenotazione nel passato! Data scelta: " + dataSelezionata);
        }
    }

    public void validaCapacitaTeeTime(String data, String orario, int nuoviGiocatori) throws InvalidReservationException, SQLException {
        int postiOccupati = dao.getPostiOccupati(data, orario);
        int postiLiberi = 4 - postiOccupati;

        if (postiLiberi < nuoviGiocatori) {
            throw new InvalidReservationException("Capacità insufficiente per lo slot delle " + orario +
                    ". Posti rimasti: " + postiLiberi + ", richiesti: " + nuoviGiocatori);
        }
    }

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