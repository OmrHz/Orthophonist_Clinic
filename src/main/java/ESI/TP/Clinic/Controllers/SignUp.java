package ESI.TP.Clinic.Controllers;

import ESI.TP.Clinic.Modules.orthophoniste.Cabinet;
import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileOutputStream;
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


        this.setCabinet(Cabinet.loadFromFile());
        String nomValue = this.nomField.getText();
        String prenomValue = this.prenomField.getText();
        String emailValue = this.emailField.getText();
        String adresseValue = this.adresseField.getText();
        String numeroTelephoneValue = this.numeroField.getText();
        String passwordValue = this.passwordField.getText();
        if (!nomValue.isEmpty() && !prenomValue.isEmpty() && !emailValue.isEmpty() && !adresseValue.isEmpty() && !numeroTelephoneValue.isEmpty() && !passwordValue.isEmpty()) {
            if (!this.cabinet.emailExists(emailValue)) {
                this.cabinet.sauvegarderMailPassword(emailValue, passwordValue);
                Orthophoniste o = new Orthophoniste(nomValue, prenomValue, adresseValue, numeroTelephoneValue, emailValue, passwordValue);
                this.showAlert("Orthophonist " + nomValue + " " + prenomValue + " added successfully!");


                try {
                    Throwable var9 = null;
                    Object var10 = null;

                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomValue + ".bin"));

                        try {
                            oos.writeObject(o);
                        } finally {
                            if (oos != null) {
                                oos.close();
                            }

                        }
                    } catch (Throwable var19) {
                        if (var9 == null) {
                            var9 = var19;
                        } else if (var9 != var19) {
                            var9.addSuppressed(var19);
                        }

                        throw var9;
                    }
                } catch (Throwable var20) {
                    var20.printStackTrace();
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

    public void handleClick(MouseEvent mouseEvent) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Login.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Login");
        try {
            stage.setScene(new javafx.scene.Scene(loader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.stage != null) {
            this.stage.close();
        }
    }
}