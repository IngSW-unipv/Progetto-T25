package it.unipv.ingsfw.controller;



import it.unipv.ingsfw.exceptions.InvalidReservationException;
import it.unipv.ingsfw.model.ReservationSession;
import it.unipv.ingsfw.service.PriceCalculator;
import it.unipv.ingsfw.service.ReservationValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

/**
 * Controller per la selezione dei servizi extra e attrezzatura (Step 6).
 *
 * @author Leah Appiah
 * @version 1.0
 */
public class Step6ExtraController {

    @FXML
    private Spinner<Integer> spCart;

    @FXML
    private Spinner<Integer> spGolfbag;

    @FXML
    private Spinner<Integer> spTrolley;

    private final ReservationValidator validator = new ReservationValidator();

    @FXML
    public void initialize() {
        spCart.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4, 0));
        spGolfbag.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4, 0));
        spTrolley.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4, 0));
    }

    @FXML
    private void onAvanti(ActionEvent e) {
        try {
            int numeroGiocatori = ReservationSession.getInstance().getPlayers();

            validator.validaAttrezzatura(numeroGiocatori, spGolfbag.getValue(), spCart.getValue(), spTrolley.getValue());
            String extraText = spCart.getValue() + " Cart, " + spGolfbag.getValue() + " Sacche, " + spTrolley.getValue() + " Trolley";
            ReservationSession.getInstance().setExtra(extraText);
            ReservationSession.getInstance().setCarts(spCart.getValue());
            ReservationSession.getInstance().setBags(spGolfbag.getValue());
            ReservationSession.getInstance().setTrolleys(spTrolley.getValue());
            double total = PriceCalculator.calcolaTotale(
                    ReservationSession.getInstance().getHoles(),
                    ReservationSession.getInstance().getPlayers(),
                    spCart.getValue(),
                    spGolfbag.getValue(),
                    spTrolley.getValue()
            );
            ReservationSession.getInstance().setTotalPrice(total);
            Parent root = FXMLLoader.load(getClass().getResource("/view/step7Riepilogo.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);

        } catch (InvalidReservationException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore Validazione");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di navigazione");
            alert.setHeaderText("Impossibile caricare la schermata di riepilogo");
            alert.showAndWait();
        }
    }

    @FXML
    private void onIndietro(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/step5Nome.fxml"));
            Scene currentScene = ((Node) e.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di navigazione");
            alert.setHeaderText("Impossibile tornare alla schermata di inserimento del nome");
            alert.showAndWait();
        }
    }
}