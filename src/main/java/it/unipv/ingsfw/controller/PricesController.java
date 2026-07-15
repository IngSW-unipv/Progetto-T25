package it.unipv.ingsfw.controller;



import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;


/**
 * Controller per la schermata del listino prezzi.
 *
 * @author Leah Appiah
 * @version 1.0
 */
public class PricesController {

    @FXML
    private void onIndietro(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));

            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Impossibile caricare la Home Page.");
            alert.showAndWait();
        }
    }
}