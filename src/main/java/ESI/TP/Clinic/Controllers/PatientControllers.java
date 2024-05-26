package ESI.TP.Clinic.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.io.IOException;
import java.time.LocalDate;
import ESI.TP.Clinic.Modules.orthophoniste.*;
import ESI.TP.Clinic.Modules.patient.*;

public class PatientControllers {
    private Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }
    public void handleEnfantButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Enfant.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EnfantControllers controller = loader.getController();
        controller.setOrthophoniste(orthophoniste);
        stage.setScene(new Scene(root));
        stage.setTitle("Sign-Up");
        stage.show();
    }

    public void handleAdultButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Adulte.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AdulteControllers controller = loader.getController();
        controller.setOrthophoniste(orthophoniste);
        stage.setScene(new Scene(root));
        stage.setTitle("Sign-Up");
        stage.show();
    }
}
