package ESI.TP.Clinic.Controllers;
import ESI.TP.Clinic.Modules.RDV.AjoutRdvException;
import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import ESI.TP.Clinic.Modules.patient.DossierPatient;
import ESI.TP.Clinic.Modules.patient.Patient;
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
import java.util.Map;

public class AtelierControllers {
    @FXML
    private ComboBox<String> hourComboBox;

    @FXML
    private ComboBox<String> minuteComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField themTextField;

    @FXML
    private TextArea additionalInfoTextArea;
    @FXML
    private Button RetourButton;
    @FXML
    private Button seDeconnecterButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Text lastname;
    private Orthophoniste orthophoniste;
    private Map<CheckBox, Integer> checkBoxPatientMap = new HashMap<>();

    int yPos=14;
    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
        lastname.setText("Dr "+orthophoniste.getCompte().getNom());
        HashMap<Patient, DossierPatient> patientHashMap = orthophoniste.getPatients();
        patientHashMap.entrySet().forEach(entry -> {
            Patient patient = entry.getKey();
            DossierPatient dossier = entry.getValue();
            // Add only patient names that haven't had their first consultation
            if(!(dossier.getListeRendezVous().isEmpty())) {
                CheckBox checkBox = new CheckBox();
                checkBox.setText(patient.getNom() + ", num Dossier: " + dossier.getNumeroDossier());
                checkBox.setLayoutX(13.0);
                checkBox.setLayoutY(yPos);
                checkBox.setMnemonicParsing(false);
                anchorPane.getChildren().add(checkBox);
                checkBoxPatientMap.put(checkBox, dossier.getNumeroDossier());
                yPos += 24;  // Increment Y position for the next CheckBox
            }   });

    }
    @FXML
    public void initialize() {
        RetourButton.setOnAction(this::handleRetourButtonAction);
        seDeconnecterButton.setOnAction(this::handleSeDeconnecterButtonAction);
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
    public void handleValider(ActionEvent event){
        LocalDate date = datePicker.getValue();
        int hour = Integer.parseInt(hourComboBox.getValue());
        int minute = Integer.parseInt(minuteComboBox.getValue());
        String theme = themTextField.getText();
        String additionalInfo = additionalInfoTextArea.getText();
        if (hourComboBox == null || minuteComboBox == null || date == null|| theme.isEmpty()) {
            showAlert("Error, Please fill in all required fields.");
            return;
        }
        ArrayList<Integer> dossierNumbers = new ArrayList<>();
        for (Map.Entry<CheckBox, Integer > entry : checkBoxPatientMap.entrySet()) {
            CheckBox checkBox = entry.getKey();
            if (checkBox.isSelected()) {
                int n = entry.getValue();
                 dossierNumbers.add(n);
            }
        }
        try {
            LocalDateTime dateTime = date.atTime(hour, minute);
            orthophoniste.ProgrammerAtelierDeGroupe(dateTime, theme, dossierNumbers);
            showAlert("added succcessfully");
        } catch (AjoutRdvException e) {
            showAlert(e.getMessage());
        }finally {
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

    }
    public void handleAnnuler(ActionEvent event) {
        // Clear all the fields
        hourComboBox.setValue(null);
        minuteComboBox.setValue(null);
        datePicker.setValue(null);
        themTextField.clear();
        additionalInfoTextArea.clear();
        showAlert("Feilds are Empty");
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText((String)null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

