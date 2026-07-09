package it.unipv.ingsfw.service;


import it.unipv.ingsfw.dao.ReservationDao;
import it.unipv.ingsfw.model.Reservation;
import it.unipv.ingsfw.model.TeeTimeSlot;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * classe per la generazione del planning per slot orario
 */

public class TeeTimePlanningService {

    private final ReservationDao dao = new ReservationDao();

    public TeeTimePlanningService() {
    }

    public List<TeeTimeSlot> generaPlanningGiornaliero(LocalDate dataSelezionata) throws SQLException {

        List<TeeTimeSlot> righePlanning = new ArrayList<>();
        List<Reservation> tutteLePrenotazioni = dao.getAll();
        List<Reservation> filtratePerData = tutteLePrenotazioni.stream()
                .filter(r -> r.getDate().equals(dataSelezionata))
                .collect(Collectors.toList());
        for (int ora = 8; ora <= 11; ora++) {
            for (int minuto = 0; minuto < 60; minuto += 10) {
                String orarioSlot = String.format("%02d:%02d", ora, minuto);
                righePlanning.add(mappaSingoloSlot(orarioSlot, filtratePerData));
            }
        }

        righePlanning.add(mappaSingoloSlot("12:00", filtratePerData));

        return righePlanning;

    }

    private TeeTimeSlot mappaSingoloSlot(String orarioSlot, List<Reservation> prenotazioniGiorno) {

        List<Reservation> pPerOrario = prenotazioniGiorno.stream()
                .filter(p -> p.getTime().equals(orarioSlot))
                .collect(Collectors.toList());

        int sommaGiocatori = pPerOrario.stream().mapToInt(Reservation::getPlayers).sum();

        String dettagli = pPerOrario.stream()
                .map(p -> p.getContactName() + " (" + p.getPlayers() + "p)")
                .collect(Collectors.joining(", "));

        String stato;
        if (sommaGiocatori == 0) {
            stato = "LIBERO (0/4)";
            dettagli = "- Nessun giocatore -";
        } else if (sommaGiocatori >= 4) {
            stato = "COMPLETO (4/4)";
        } else {
            stato = "PARZIALE (" + sommaGiocatori + "/4)";
        }
        return new TeeTimeSlot(orarioSlot, stato, dettagli);
    }
}