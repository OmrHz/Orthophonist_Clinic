package ESI.TP.Clinic.Controllers;

import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import ESI.TP.Clinic.Modules.patient.Enfant;
import ESI.TP.Clinic.Modules.patient.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.Period;

public class EnfantControllers {
    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField adresseField;

    @FXML
    private TextField NiveauField;

    @FXML
    private TextField lieuNaissanceField;

    @FXML
    private DatePicker dateNaissanceField;

    @FXML
    private TextField numeroMereField;

    @FXML
    private TextField numeroPereField;

    private Orthophoniste orthophoniste;
    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }
    @FXML
    public void handleSubmit(ActionEvent event) {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String adresse = adresseField.getText();
        String niveau = NiveauField.getText();
        String lieuNaissance = lieuNaissanceField.getText();
        LocalDate dateNaissance = dateNaissanceField.getValue();
        String numeroMere = numeroMereField.getText();
        String numeroPere = numeroPereField.getText();
        if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || niveau.isEmpty() || lieuNaissance.isEmpty() || dateNaissance == null || numeroMere.isEmpty() || numeroPere.isEmpty()) {
            showAlert("Please fill in all the fields");
            return;
        }
        int age = calculateAge(dateNaissance, LocalDate.now());
        Patient patient;
        if (age>=18){
            showAlert("This form is for children only");
            return;
        }else{
            patient =new Enfant(nom,prenom,adresse,dateNaissance,lieuNaissance,niveau,numeroMere,numeroPere);
            orthophoniste.priseEnCharge(patient);
            Orthophoniste.SaveOrthophonisteFromFile(orthophoniste.getCompte().getNom(), orthophoniste);
            showAlert("Patient saved!");
            // clear the fields
            nomField.clear();
            prenomField.clear();
            adresseField.clear();
            NiveauField.clear();
            lieuNaissanceField.clear();
            dateNaissanceField.getEditor().clear();
            numeroMereField.clear();
            numeroPereField.clear();

        }
    }
    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if (birthDate == null || currentDate == null) {
            return 0;
        }
        return Period.between(birthDate, currentDate).getYears();
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText((String)null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
