package ESI.TP.Clinic.Controllers;

import ESI.TP.Clinic.Modules.RDV.AjoutRdvException;
import ESI.TP.Clinic.Modules.RDV.typeSeanceSuivi;
import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import ESI.TP.Clinic.Modules.patient.DossierPatient;
import ESI.TP.Clinic.Modules.patient.Patient;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class RdvSuiviController {
    @FXML
    private ComboBox<String> hourComboBox;

    @FXML
    private ComboBox<String> minuteComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea additionalInfoTextArea;

    @FXML
    private ComboBox<typeSeanceSuivi> deroulement;
    @FXML
    private ComboBox<Integer> patient;
    @FXML
    private Button RetourButton;
    @FXML
    private Button seDeconnecterButton;
    @FXML
    private Text lastname;
    private Orthophoniste orthophoniste;
    ArrayList <Integer> patientNames = new ArrayList<>();
    public void setOrthophoniste(Orthophoniste ort) {
        this.orthophoniste = ort;
        lastname.setText(" Dr "+orthophoniste.getCompte().getNom());
        HashMap<Patient, DossierPatient> patientHashMap = orthophoniste.getPatients();
        patientHashMap.entrySet().forEach(entry -> {
            Patient patient = entry.getKey();
            DossierPatient dossier = entry.getValue();
            if(!(dossier.getListeRendezVous().isEmpty())) {
                patientNames.add(dossier.getNumeroDossier());
            }});
        patient.setItems(FXCollections.observableArrayList(patientNames));
    }

    @FXML
    public void initialize() {
        seDeconnecterButton.setOnAction(this::handleSeDeconnecterButtonAction);
        RetourButton.setOnAction(this::handleRetourButtonAction);
        deroulement.setItems(FXCollections.observableArrayList(typeSeanceSuivi.values()));
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
        stage.setTitle("Accueil");
        stage.show();
    }
    @FXML
    private void handleSeDeconnecterButtonAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Hello.fxml"));
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
        stage.setTitle("Hello");
        stage.show();
    }
    @FXML
    public void HandleAnnuler(ActionEvent event) {
        hourComboBox.setValue(null);
        minuteComboBox.setValue(null);
        datePicker.setValue(null);

    }

    public void handleValider(ActionEvent event) throws AjoutRdvException{
        LocalDate date = datePicker.getValue();
        int hour = Integer.parseInt(hourComboBox.getValue());
        int minute = Integer.parseInt(minuteComboBox.getValue());
        String Info = additionalInfoTextArea.getText();
        typeSeanceSuivi Deroulement = deroulement.getValue() ;
        // get the selected patient
        Integer selectedPatient = patient.getValue();
        if (hourComboBox == null || minuteComboBox == null || date == null|| Info.isEmpty()) {
            showAlert("Error, Please fill in all required fields.");
            return;
        }
        LocalDateTime dateTime = date.atTime(hour,minute);
        try {
            orthophoniste.ProgrammerSeanceDeSuivi(dateTime,selectedPatient, Deroulement);
            showAlert("added successfully");
            System.out.println("added");
        } catch (AjoutRdvException e) {
            showAlert(e.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            Orthophoniste.SaveOrthophonisteFromFile(this.orthophoniste.getCompte().getNom(), orthophoniste);
            try {
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Accueil.fxml"));
                Parent root = loader.load();
                AccueilController controller = loader.getController();
                controller.setOrthophoniste(this.orthophoniste); // Pass the Orthophoniste object to the controller
                stage.setScene(new Scene(root));
                stage.setTitle("Accueil");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }                }
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText((String)null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
