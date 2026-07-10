package it.unipv.ingsfw.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;


/**
 * classe del controller con la schermata home del portale delle prenotazioni
 */
public class HomeController {

    @FXML
    private void onNuovaPrenotazione(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/step1Buche.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onPrices(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/prices.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onSettingsClick(MouseEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/AdminLogin.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}