package ESI.TP.Clinic.Controllers;
import ESI.TP.Clinic.Modules.orthophoniste.Cabinet;
import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    private Cabinet cabinet;
    public void HandleSeConnecter(ActionEvent event) {
        // the default cabinet
        setCabinet(Cabinet.loadFromFile("Cabinet"));
        String emailValue = emailField.getText();
        String passwordValue = passwordField.getText();
        if (cabinet != null) {

            if (cabinet.authenticate(emailValue, passwordValue, cabinet.getNomCabinet())) {
                Orthophoniste ort = new Orthophoniste();
                ort.copyFrom(Orthophoniste.loadOrthophonisteFromFile(cabinet.getListeMailPassword().get(emailValue).get(0)));
                try {

                    Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Accueil.fxml"));
                    Parent root = loader.load();
                    AccueilController controller = loader.getController();
                    controller.setOrthophoniste(ort);
                    stage.setScene(new Scene(root));
                    stage.setTitle("Home page");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (cabinet.emailExists(emailValue, cabinet.getNomCabinet())) {
                showAlert("Wrong password !");
            } else {
                showAlert("Email does not exist !");
            }
        } else {
            showAlert("Cabinet is not initialized!");
        }

    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }

    public void handleCliked(MouseEvent actionEvent) {

        // Get the current stage
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/SignUP.fxml"));
        try {
            currentStage.setScene(new javafx.scene.Scene(loader.load()));
            currentStage.setTitle("Add Orthophonist");
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
