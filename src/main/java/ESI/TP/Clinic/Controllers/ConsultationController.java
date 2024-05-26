package ESI.TP.Clinic.Controllers;


import ESI.TP.Clinic.Modules.RDV.AjoutRdvException;
import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import ESI.TP.Clinic.Modules.patient.DossierPatient;
import ESI.TP.Clinic.Modules.patient.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ConsultationController {
    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField motifField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> hourComboBox;

    @FXML
    private ComboBox<String> minuteComboBox;

    private Orthophoniste orthophoniste;
    @FXML
    private Button RetourButton;

    @FXML
    private Button seDeconnecterButton;
    @FXML
    private Text lastname;
    @FXML
    public void initialize() {
        RetourButton.setOnAction(this::handleRetourButtonAction);
        seDeconnecterButton.setOnAction(this::handleSeDeconnecterButtonAction);
    }
    @FXML
    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
        this.lastname.setText("DR " + orthophoniste.getCompte().getNom());

    }

    @FXML
    private void handleRetourButtonAction(ActionEvent event) {
        // Your logic to handle retour button action
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Accueil.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
            AccueilController controller = loader.getController();
            controller.setOrthophoniste(orthophoniste);
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
        stage.show();
    }

    public void handleAnnuler(ActionEvent event) {
        // Clear all the fields
        nomField.clear();
        prenomField.clear();
        ageField.clear();
        motifField.clear();
        datePicker.setValue(null);
        hourComboBox.setValue(null);
        minuteComboBox.setValue(null);
        showAlert("Feilds are Empty");
    }
    boolean entryFound = false;
    public void handleValider(ActionEvent event) throws AjoutRdvException {
        String Nom  = nomField.getText();
        String Prenom = prenomField.getText();
//        int Age = Integer.parseInt(ageField.getText());
        String Motif = motifField.getText();
        LocalDate date = datePicker.getValue();
        int hour = Integer.parseInt(hourComboBox.getValue());
        int minute = Integer.parseInt(minuteComboBox.getValue());
        if (Nom.isEmpty() || Prenom.isEmpty() || Motif.isEmpty() || date == null || hourComboBox.getValue() == null || minuteComboBox.getValue() == null) {
            showAlert("Please fill in all the fields");
            return;
        }

        LocalDateTime rdvDate = date.atTime(hour, minute);
        HashMap<Patient, DossierPatient> patientHashMap = this.orthophoniste.getPatients();
        patientHashMap.entrySet().forEach(entry -> {
            Patient patient = entry.getKey();
            DossierPatient dossier = entry.getValue();
            // Add only patient names that haven't had their first consultation
                if(patient.getNom().equals(Nom) && patient.getPrenom().equals(Prenom)){
                       if (dossier.getListeRendezVous().isEmpty()) {
                           try{
                               entryFound = true; // Set the flag to true if an entry satisfies the conditions
                           orthophoniste.ProgrammerConsultation(rdvDate, patient);
                           showAlert("Consultation ajouté avec succès");
                           }catch (AjoutRdvException e){
                             showAlert(e.getMessage());
                           } finally {
                               Orthophoniste.SaveOrthophonisteFromFile(this.orthophoniste.getCompte().getNom(), orthophoniste);
                               try {
                               Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                               FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Accueil.fxml"));
                               Parent root = loader.load();
                               AccueilController controller = loader.getController();
                               controller.setOrthophoniste(orthophoniste); // Pass the Orthophoniste object to the controller
                               stage.setScene(new Scene(root));
                               stage.setTitle("Home page");
                               stage.show();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                           }
                       }else {
                          showAlert("Le patient déja fait ou programmer une consultation");
                       }
           }});
        if (!entryFound) {
            showAlert("Aucun patient trouvé avec le nom et prénom spécifiés");
        }
          }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText((String)null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
