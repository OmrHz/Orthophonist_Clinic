package ESI.TP.Clinic.Controllers;

import ESI.TP.Clinic.Modules.orthophoniste.Cabinet;
import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SignUp {
    private Cabinet cabinet;
    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField adresseField;

    @FXML
    private TextField numeroField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;
    private Stage stage;
    public SignUp() {
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }
    @FXML
    private void handleSubmit() {
        // the default cabinet
        setCabinet(Cabinet.loadFromFile("Cabinet"));
        String nomValue = this.nomField.getText();
        String prenomValue = this.prenomField.getText();
        String emailValue = this.emailField.getText();
        String adresseValue = this.adresseField.getText();
        String numeroTelephoneValue = this.numeroField.getText();
        String passwordValue = this.passwordField.getText();
        if (!nomValue.isEmpty() && !prenomValue.isEmpty() && !emailValue.isEmpty() && !adresseValue.isEmpty() && !numeroTelephoneValue.isEmpty() && !passwordValue.isEmpty()) {
            if (!cabinet.emailExists(emailValue,cabinet.getNomCabinet())) {
                cabinet.sauvegarderMailPassword(nomValue,emailValue, passwordValue,cabinet.getNomCabinet());
                Orthophoniste o = new Orthophoniste(nomValue, prenomValue, adresseValue, numeroTelephoneValue, emailValue, passwordValue);
                showAlert("Orthophonist " + nomValue + " " + prenomValue + " added successfully!");
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomValue + ".bin"))) {
                    oos.writeObject(o);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                this.nomField.clear();
                this.prenomField.clear();
                this.emailField.clear();
                this.adresseField.clear();
                this.numeroField.clear();
                this.passwordField.clear();
            } else {
                this.showAlert("Email already exists. Please use a different email.");
            }

        } else {
            this.showAlert("Please fill in all fields.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText((String)null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    public void handleClick(MouseEvent actionEvent) {
        // Get the current stage
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Login.fxml"));
        try {
            currentStage.setScene(new javafx.scene.Scene(loader.load()));
            currentStage.setTitle("Login Page");
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}