package it.unipv.ingsfw.controller;


import it.unipv.ingsfw.model.TeeTimeSlot;
import it.unipv.ingsfw.service.TeeTimePlanningService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.List;

/**
 * classe planning
 */
public class TeeTimeGridController {

    @FXML
    private DatePicker dpDataSelezionata;

    @FXML
    private TableView<TeeTimeSlot> tblGTMGrid;

    @FXML
    private TableColumn<TeeTimeSlot, String> colOrario;

    @FXML
    private TableColumn<TeeTimeSlot, String> colStato;

    @FXML
    private TableColumn<TeeTimeSlot, String> colDettagli;

    private final TeeTimePlanningService planningService = new TeeTimePlanningService();

    @FXML
    public void initialize() {
        colOrario.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStato.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDettagli.setCellValueFactory(new PropertyValueFactory<>("details"));
        dpDataSelezionata.setValue(LocalDate.now());
        aggiornaTabellaOraria();
    }

    @FXML
    private void onCambioData(ActionEvent event) {
        aggiornaTabellaOraria();
    }

    private void aggiornaTabellaOraria() {
        LocalDate dataSel = dpDataSelezionata.getValue();

        if (dataSel == null) return;

        try {
            List<TeeTimeSlot> risultatoPlanning = planningService.generaPlanningGiornaliero(dataSel);

            ObservableList<TeeTimeSlot> righeTabella = FXCollections.observableArrayList(risultatoPlanning);
            tblGTMGrid.setItems(righeTabella);
            tblGTMGrid.refresh();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onIndietro(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/admin_dashboard.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}