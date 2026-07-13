package it.unipv.ingsfw.controller;

import it.unipv.ingsfw.model.ReservationSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;


/**
 * classe secondo step scelta giocatore
 */
public class Step2GiocatoriController {

    @FXML
    private Spinner<Integer> spPlayers;

    @FXML
    public void initialize() {
        spPlayers.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1));
    }

    @FXML
    private void onAvanti(ActionEvent e) {
        ReservationSession.getInstance().setPlayers(spPlayers.getValue());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/step3Data.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onIndietro(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/step1Buche.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}