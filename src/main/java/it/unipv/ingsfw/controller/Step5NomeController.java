package it.unipv.ingsfw.controller;



import it.unipv.ingsfw.model.ReservationSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Controller per l'inserimento del nome del cliente (Step 5).
 *
 * @author Leah Appiah
 * @version 1.0
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di navigazione");
            alert.setHeaderText("Impossibile caricare la schermata di selezione dei servizi extra");
            alert.showAndWait();
        }
    }

    @FXML
    private void onIndietro(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/step4Orario.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di navigazione");
            alert.setHeaderText("Impossibile tornare alla schermata di selezione dell'orario");
            alert.showAndWait();
        }
    }
}