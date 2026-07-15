package it.unipv.ingsfw.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;


/**
 * Controller per la schermata principale (Home Page) del sistema GTM.
 * Smista l'utente verso gli step di prenotazione, il listino prezzi o l'area amministrativa.
 *
 * @author Leah Appiah
 * @version 1.0
 */
public class HomeController {

    @FXML
    private void onNuovaPrenotazione(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/step1Buche.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di navigazione");
            alert.setHeaderText("Impossibile caricare la schermata di navigazione");
            alert.showAndWait();
        }
    }

    @FXML
    private void onPrices(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/prices.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di caricamento");
            alert.setHeaderText("Impossibile caricare il listino prezzi");
            alert.showAndWait();
        }
    }

    @FXML
    private void onSettingsClick(MouseEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/AdminLogin.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di accesso");
            alert.setHeaderText("Impossibile aprire la pagina di login");
            alert.showAndWait();
        }
    }
}