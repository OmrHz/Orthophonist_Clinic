package ESI.TP.Clinic.Controllers;
import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ParametresController {
    @FXML
    private Button RdvSideBar;

    @FXML
    private Button DossierSideBar;

    @FXML
    private Button TestSideBar;

    @FXML
    private Button StatSideBar;

    @FXML
    private Button RetourButton;

    @FXML
    private Button seDeconnecterButton;
    private Orthophoniste orthophoniste;

    @FXML
    public void initialize() {
        RetourButton.setOnAction(this::handleRetourButtonAction);
        seDeconnecterButton.setOnAction(this::handleSeDeconnecterButtonAction);
        RdvSideBar.setOnAction(this::handleRdvSideBarAction);
        DossierSideBar.setOnAction(this::handleDossierSideBarAction);
        TestSideBar.setOnAction(this::handleTestSideBarAction);
        StatSideBar.setOnAction(this::handleStatSideBarAction);
    }

    private void handleStatSideBarAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Stat.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
        StatisticsController controller = loader.getController();
        controller.setOrthophoniste(orthophoniste);
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void handleTestSideBarAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Bilan.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
        BilanController controller = loader.getController();
        controller.setOrthophoniste(orthophoniste);
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void handleDossierSideBarAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Dossiers.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
        DossiersController controller = loader.getController();
        controller.setOrthophoniste(orthophoniste);
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void handleRdvSideBarAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Rendez-vous.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
         TypeRdvController controller = loader.getController();
        controller.setOrthophoniste(orthophoniste);
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleSeDeconnecterButtonAction(ActionEvent event) {
        // Your logic to handle se deconnecter button action
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Login.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }

        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void handleRetourButtonAction(ActionEvent event) {
        // Your logic to handle retour button action
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Accueil.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
        AccueilController controller = loader.getController();
        controller.setOrthophoniste(orthophoniste);
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
      @FXML
      private Text lastname;
    public void setOrthophoniste(Orthophoniste ort) {
        this.orthophoniste = ort;
        lastname.setText(orthophoniste.getCompte().getNom());

    }
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;

    public void HandleValider(ActionEvent event) {
        if(nameField.getText().isEmpty() || phoneField.getText().isEmpty() || emailField.getText().isEmpty()) {
            showAlert("Veuillez remplir tous les champs");
            return;
        }
        this.orthophoniste.getCompte().setNom(nameField.getText());
        this.orthophoniste.getCompte().setPhone(phoneField.getText());
        this.orthophoniste.getCompte().setEmail(emailField.getText());
        showAlert("Les informations ont été modifiées avec succès");
        nameField.clear();
        phoneField.clear();
        emailField.clear();
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText((String)null);
        alert.setContentText(message);
        alert.showAndWait();
    }
        @FXML
    private PasswordField pass;
    public void HandleChangePass(ActionEvent event) {
        if(pass.getText().isEmpty()) {
            showAlert("Veuillez remplir le champ mot de passe");
            return;
        }
        this.orthophoniste.getCompte().setPassword(pass.getText());
        showAlert("Le mot de passe a été modifié avec succès");
        pass.clear();
    }
}
