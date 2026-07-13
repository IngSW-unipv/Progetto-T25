package it.unipv.ingsfw.controller;


import it.unipv.ingsfw.dao.ReservationDao;
import it.unipv.ingsfw.model.Reservation;
import it.unipv.ingsfw.model.ReservationSession;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 * classe riepilogo prenotazione
 */
public class Step7RiepilogoController {

    @FXML
    private Label lblName;

    @FXML
    private Label lblHoles;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblPlayers;

    @FXML
    private Label lblExtras;

    @FXML
    private Label lblTotal;

    @FXML
    public void initialize() {
        if (ReservationSession.getInstance().getCustomerName() != null) {
            lblName.setText(ReservationSession.getInstance().getCustomerName());
        }
        lblHoles.setText(String.valueOf(ReservationSession.getInstance().getHoles()));
        lblTime.setText(ReservationSession.getInstance().getTime());
        lblPlayers.setText(String.valueOf(ReservationSession.getInstance().getPlayers()));
        lblExtras.setText(ReservationSession.getInstance().getExtra());
        lblTotal.setText("€ " + ReservationSession.getInstance().getTotalPrice());

        if (ReservationSession.getInstance().getDate() != null && lblDate != null) {
            lblDate.setText(ReservationSession.getInstance().getDate().toString());
        }
    }

    @FXML
    private void onConferma(ActionEvent e) {
        try {
            Reservation r = new Reservation();
            r.setContactName(ReservationSession.getInstance().getCustomerName());
            r.setHoles(ReservationSession.getInstance().getHoles());
            r.setTime(ReservationSession.getInstance().getTime());
            r.setPlayers(ReservationSession.getInstance().getPlayers());
            r.setExtra(ReservationSession.getInstance().getExtra());
            r.setTotalPrice(ReservationSession.getInstance().getTotalPrice());
            if (ReservationSession.getInstance().getDate() != null) {
                r.setDate(ReservationSession.getInstance().getDate());
            } else {
                r.setDate(java.time.LocalDate.now());
            }
            ReservationDao dao = new ReservationDao();
            dao.save(r);
            Alert ok = new Alert(Alert.AlertType.INFORMATION);
            ok.setTitle("Conferma");
            ok.setHeaderText(null);
            ok.setContentText("Prenotazione confermata!");
            ok.showAndWait();
            ReservationSession.getInstance().reset();
            Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert errore = new Alert(Alert.AlertType.ERROR);
            errore.setTitle("Errore");
            errore.setHeaderText(null);
            errore.setContentText("Errore nel salvataggio su database!");
            errore.showAndWait();
        }
    }

    @FXML
    private void onIndietro(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/step6Extra.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}