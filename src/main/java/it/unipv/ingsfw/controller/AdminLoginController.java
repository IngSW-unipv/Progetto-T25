package it.unipv.ingsfw.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import java.io.InputStream;
import java.util.Properties;

public class AdminLoginController {

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Label lblError;

    @FXML
    private void onAccedi(ActionEvent e) {

        String inputPassword = pfPassword.getText();

        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                lblError.setText("File di configurazione non trovato.");
                return;
            }
            prop.load(input);
            String securedPassword = prop.getProperty("gtm.admin.password");

            if (inputPassword.equals(securedPassword)) {
                Parent root = FXMLLoader.load(getClass().getResource("/view/admin_dashboard.fxml"));
                Scene currentScene = ((Node) e.getSource()).getScene();
                currentScene.setRoot(root);
            } else {
                lblError.setText("Password errata!");
            }
        } catch (Exception ex) {
            lblError.setText("Errore di caricamento del sistema.");
            ex.printStackTrace();
        }
    }

    @FXML
    private void onIndietro(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}