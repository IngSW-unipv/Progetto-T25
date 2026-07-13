package it.unipv.ingsfw.controller;



import it.unipv.ingsfw.model.ReservationSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

/**
 * classe scelta nome
 */
public class Step5NomeController {

    @FXML
    private TextField tfNome;

    @FXML
    private void onAvanti(ActionEvent e) {
        if (tfNome.getText() == null || tfNome.getText().trim().isEmpty()) {
            return;
        }
        ReservationSession.getInstance().setCustomerName(tfNome.getText().trim());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/step6Extra.fxml"));

            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onIndietro(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/step4Orario.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}