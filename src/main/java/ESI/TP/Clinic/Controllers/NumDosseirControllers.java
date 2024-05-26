package ESI.TP.Clinic.Controllers;

import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import ESI.TP.Clinic.Modules.patient.DossierPatient;
import ESI.TP.Clinic.Modules.patient.Patient;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class NumDosseirControllers {

    @FXML
    private ComboBox<Integer> numComboBox;
    private Orthophoniste orthophoniste;

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
        ArrayList<Integer> patientNumbre = new ArrayList<>();
        HashMap<Patient, DossierPatient> patientHashMap = orthophoniste.getPatients();
        patientHashMap.entrySet().forEach(entry -> {
            Patient patient = entry.getKey();
            DossierPatient dossier = entry.getValue();
            if(!(dossier.getListeRendezVous().isEmpty())) {
                patientNumbre.add(dossier.getNumeroDossier());
            }});
        numComboBox.setItems(FXCollections.observableArrayList(patientNumbre));
    }
    @FXML
    public void handleValider(ActionEvent event) throws IOException {
        // Get the current stage
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Dossier.fxml"));
        Parent root = loader.load();

        // Get the controller from the loader after loading
        DossierController dossierController = loader.getController();
        dossierController.setOrthophoniste(orthophoniste);
        dossierController.setNumDossier(numComboBox.getValue());

        // Set the scene to the stage
        stage.setScene(new Scene(root));
        stage.setTitle("Dossier Patient");
        stage.show();
    }

}
