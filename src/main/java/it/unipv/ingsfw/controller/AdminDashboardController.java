package it.unipv.ingsfw.controller;

import it.unipv.ingsfw.dao.ReservationDao;
import it.unipv.ingsfw.model.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.List;


/**
 * Controller per la visualizzazione e gestione di tutte le prenotazioni (Dashboard Admin).
 *
 * @author Leah Appiah
 * @version 1.0
 */
public class AdminDashboardController {

    @FXML
    private DatePicker dpDate;

    @FXML
    private TableView<Reservation> tblReservations;

    @FXML
    private TableColumn<Reservation, Integer> colId;

    @FXML
    private TableColumn<Reservation, String> colName;

    @FXML
    private TableColumn<Reservation, String> colDate;

    @FXML
    private TableColumn<Reservation, String> colTime;

    @FXML
    private TableColumn<Reservation, Integer> colHoles;

    @FXML
    private TableColumn<Reservation, Integer> colPlayers;

    @FXML
    private TableColumn<Reservation, String> colExtras;

    @FXML
    private TableColumn<Reservation, Double> colPrice;

    @FXML
    private Button btnGrid;

    @FXML
    private void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colHoles.setCellValueFactory(new PropertyValueFactory<>("holes"));
        colPlayers.setCellValueFactory(new PropertyValueFactory<>("players"));
        colExtras.setCellValueFactory(new PropertyValueFactory<>("extra"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        caricaDati();
    }

    private void caricaDati() {
        try {
            ReservationDao dao = new ReservationDao();

            List<Reservation> tutte = dao.getAll();
            ObservableList<Reservation> obsList = FXCollections.observableArrayList(tutte);
            tblReservations.setItems(obsList);
            tblReservations.refresh();

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore Dati");
            alert.setHeaderText("Impossibile caricare le prenotazioni");
            alert.showAndWait();
        }
    }

    @FXML
    private void onCerca(ActionEvent e) {
        LocalDate dataCercata = dpDate.getValue();

        if (dataCercata == null) {
            caricaDati();
        } else {
            try {
                ReservationDao dao = new ReservationDao();
                List<Reservation> tutte = dao.getAll();

                ObservableList<Reservation> filtrate = FXCollections.observableArrayList();

                for (Reservation r : tutte) {
                    if (dataCercata.equals(r.getDate())) {
                        filtrate.add(r);
                    }
                }

                tblReservations.setItems(filtrate);
                tblReservations.refresh();

            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore Ricerca");
                alert.setHeaderText("Impossibile filtrare le prenotazioni");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void onElimina(ActionEvent e) {
        Reservation selezionata = tblReservations.getSelectionModel().getSelectedItem();
        if (selezionata != null) {
            try {
                ReservationDao dao = new ReservationDao();

                dao.delete(selezionata.getId());
                dpDate.setValue(null);
                caricaDati();

                Alert ok = new Alert(Alert.AlertType.INFORMATION);
                ok.setTitle("Eliminato");
                ok.setHeaderText(null);
                ok.setContentText("Prenotazione eliminata con successo!");
                ok.showAndWait();

            } catch (Exception ex) {
                Alert errore = new Alert(Alert.AlertType.ERROR);
                errore.setTitle("Errore");
                errore.setHeaderText(null);
                errore.setContentText("Errore durante l'eliminazione dal database!");
                errore.showAndWait();
            }
        } else {
            Alert avviso = new Alert(Alert.AlertType.WARNING);
            avviso.setTitle("Attenzione");
            avviso.setHeaderText(null);
            avviso.setContentText("Seleziona prima una riga cliccandoci sopra!");
            avviso.showAndWait();
        }
    }

    @FXML
    private void onVediPlanning(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/tee_time_grid.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di navigazione");
            alert.setHeaderText("Impossibile caricare il planning");
            alert.showAndWait();
        }
    }

    @FXML
    private void onIndietro(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di navigazione");
            alert.setHeaderText("Impossibile tornare alla schermata home");
            alert.showAndWait();
        }
    }
}