package it.unipv.ingsfw.controller;

import it.unipv.ingsfw.exceptions.InvalidReservationException;
import it.unipv.ingsfw.model.ReservationSession;
import it.unipv.ingsfw.service.ReservationValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;

/**
 * Controller per la selezione della data di prenotazione (Step 3).
 *
 * @author Leah Appiah
 * @version 1.0
 */
public class Step3DataController {

    @FXML
    private DatePicker dpDate;

    private final ReservationValidator validator = new ReservationValidator();

    @FXML
    public void initialize() {
        if (dpDate != null) {
            dpDate.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.isBefore(LocalDate.now()));
                }
            });
        }
    }

    @FXML
    private void onAvanti(ActionEvent event) {
        LocalDate dataSelezionata = dpDate.getValue();

        try {
            validator.validaData(dataSelezionata);
            ReservationSession.getInstance().setDate(dataSelezionata);
            Parent root = FXMLLoader.load(getClass().getResource("/view/step4Orario.fxml"));
            Scene mainScene = ((Node) event.getSource()).getScene();
            mainScene.setRoot(root);
        } catch (InvalidReservationException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Data Non Valida");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di navigazione");
            alert.setHeaderText("Impossibile caricare la schermata di selezione dell'orario");
            alert.showAndWait();
        }
    }

    @FXML
    private void onIndietro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/step2Giocatori.fxml"));
            Scene currentScene = ((Node) event.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di navigazione");
            alert.setHeaderText("Impossibile tornare alla schermata di selezione giocatori");
            alert.showAndWait();
        }
    }
}