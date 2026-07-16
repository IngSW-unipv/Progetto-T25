package it.unipv.ingsfw.controller;


import it.unipv.ingsfw.model.ReservationSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;


/**
 * Classe che rappresenta il punto di ingresso degli step di prenotazione del circolo.
 * Consente all'operatore di selezionare il percorso di gioco desiderato tra 9 e 18 buche.
 *
 * @author Leah Appiah
 * @version 1.0
 */
public class Step1BucheController {

    @FXML
    private Button btn18;

    @FXML
    private Button btn9;

    @FXML
    public void initialize() {
        ReservationSession.getInstance().reset();
    }

    @FXML
    private void onSelect18(ActionEvent event) {
        ReservationSession.getInstance().setHoles(18);
        btn18.getStyleClass().add("selected-button");
        btn9.getStyleClass().remove("selected-button");
    }

    @FXML
    private void onSelect9(ActionEvent event) {
        ReservationSession.getInstance().setHoles(9);
        btn9.getStyleClass().add("selected-button");
        btn18.getStyleClass().remove("selected-button");
    }

    @FXML
    private void onAvanti(ActionEvent event) {
        if (ReservationSession.getInstance().getHoles() == 0) {
            return;
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/step2Giocatori.fxml"));
            Scene currentScene = ((Node) event.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di navigazione");
            alert.setHeaderText("Impossibile caricare la schermata di selezione giocatori");
            alert.showAndWait();
        }
    }

    @FXML
    private void onIndietro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
            Scene currentScene = ((Node) event.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di navigazione");
            alert.setHeaderText("Impossibile tornare alla schermata home");
            alert.showAndWait();
        }
    }
}