package it.unipv.ingsfw.controller;


import it.unipv.ingsfw.model.ReservationSession;
import it.unipv.ingsfw.service.ReservationValidator;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import java.time.LocalDate;


/**
 * Controller per la selezione dell'orario di prenotazione (Step 4).
 *
 * @author Leah Appiah
 * @version 1.0
 */
public class Step4OrarioController {

    @FXML
    private ComboBox<String> cbTime;
    private final ReservationValidator validator = new ReservationValidator();

    @FXML
    public void initialize() {
        if (cbTime != null) {
            cbTime.getItems().clear();
            LocalDate dataSelezionata = ReservationSession.getInstance().getDate();
            int giocatoriDaPrenotare = ReservationSession.getInstance().getPlayers();

            if (dataSelezionata != null) {
                for (int ora = 8; ora <= 11; ora++) {
                    for (int minuto = 0; minuto < 60; minuto += 10) {
                        String orario = String.format("%02d:%02d", ora, minuto);

                        try {
                            validator.validaCapacitaTeeTime(dataSelezionata.toString(), orario, giocatoriDaPrenotare);

                            cbTime.getItems().add(orario);
                        } catch (Exception ex) {
                        }
                    }
                }

                try {
                    validator.validaCapacitaTeeTime(dataSelezionata.toString(), "12:00", giocatoriDaPrenotare);
                    cbTime.getItems().add("12:00");
                } catch (Exception ex) {
                }
            }
        }
    }

    @FXML
    private void onAvanti(ActionEvent e) {
        if (cbTime.getValue() != null) {

            ReservationSession.getInstance().setTime(cbTime.getValue());

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/step5Nome.fxml"));
                Scene currentScene = ((Node) e.getSource()).getScene();
                currentScene.setRoot(root);
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore di navigazione");
                alert.setHeaderText("Impossibile caricare la schermata di inserimento del nome");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void onIndietro(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/step3Data.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di navigazione");
            alert.setHeaderText("Impossibile tornare alla schermata di selezione della data");
            alert.showAndWait();
        }
    }

    @FXML
    private void onTimeSelected(ActionEvent event) {
        if (cbTime.getValue() != null) {
            ReservationSession.getInstance().setTime(cbTime.getValue());
        }
    }
}