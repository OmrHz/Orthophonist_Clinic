package ESI.TP.Clinic.Controllers;

import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class TypeRdvController {

    @FXML
    private Button ConsultationButton;

    @FXML
    private Button SuiviButton;

    @FXML
    private Button AtelierButton;

    @FXML
    private Button seDeconnecterButton;
    @FXML
    private Text fullname;

    private Orthophoniste ort;
    public void setOrthophoniste(Orthophoniste ort) {
        this.ort = ort;
        if(ort !=null ){
            this.fullname.setText("DR " + ort.getCompte().getNom() + " " + ort.getCompte().getPrenom());
        }else{
            this.fullname.setText("DR " + " " + " ");
            System.out.println(" orthophoniste not found in type RDV");
        }

    }
    @FXML
    public void initialize() {
        ConsultationButton.setOnAction(this::handleConsultationButtonAction);
        SuiviButton.setOnAction(this::handleSuiviButtonAction);
        AtelierButton.setOnAction(this::handleAtelierButtonAction);
        seDeconnecterButton.setOnAction(this::handleSeDeconnecterButtonAction);
    }

    @FXML
    private void handleConsultationButtonAction(ActionEvent event) {
        // Your logic to handle consultation button action
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Consultation.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
        ConsultationController controller = loader.getController();
        controller.setOrthophoniste(ort);
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleSuiviButtonAction(ActionEvent event) {
        // Your logic to handle suivi button action
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/RdvSuivi.fxml"));
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
    private void handleAtelierButtonAction(ActionEvent event) {
        // Your logic to handle atelier button action
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Atelier.fxml"));
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
}
