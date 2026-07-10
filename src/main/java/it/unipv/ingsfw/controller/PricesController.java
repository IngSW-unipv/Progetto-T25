package it.unipv.ingsfw.controller;



import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * listino prezzi
 */
public class PricesController {

    @FXML
    private void onIndietro(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));

            Scene currentScene = ((Node) e.getSource()).getScene();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}