package ESI.TP.Clinic.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import ESI.TP.Clinic.Modules.BO.Trouble;
import ESI.TP.Clinic.Modules.patient.*;
import javafx.scene.control.Button;

import java.io.IOException;

public class AjouterTroublesController {
//    Patient patient = Patient.getInstance();
//    @FXML
//    private Button retourID;
//
//    @FXML
//    private CheckBox cognitifID;
//
//    @FXML
//    private CheckBox deglutitionID;
//
//    @FXML
//    private CheckBox neuroID;
//
//    @FXML
//    void cognitif(ActionEvent event) {
//        handleCheckbox(cognitifID, Trouble.COGNITIF);
//    }
//
//    @FXML
//    void deglutition(ActionEvent event) {
//        handleCheckbox(deglutitionID, Trouble.DEGLUTION);
//    }
//
//    @FXML
//    void neuro(ActionEvent event) {
//        handleCheckbox(neuroID, Trouble.NEURODEVELOPPEMENTAUX);
//    }
//
//    @FXML
//    void retourAccueil(ActionEvent event) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PageAcceuil.fxml"));
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(fxmlLoader.load(), 1170, 600);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
//    }
//
//    public void initialize() {
//        // Initialize the state of the checkboxes based on the patient's existing troubles
//        System.out.println("TEST : Patient TROUBLESSSSS  son nom est "+ patient.getNom() );
//
//        if (patient.getDossier().getListeTroubles().contains(Categorie_trouble.TROUBLES_COGNITIFS)) {
//            cognitifID.setSelected(true);
//        }
//        if (patient.getDossier().getListeTroubles().contains(Categorie_trouble.TROUBLES_DE_LA_DEGLUTITION)) {
//            deglutitionID.setSelected(true);
//        }
//        if (patient.getDossier().getListeTroubles().contains(Categorie_trouble.TROUBLES_NEURO_DEVELOPPEMENTAUX)) {
//            neuroID.setSelected(true);
//        }
//    }
//
//    private void handleCheckbox(CheckBox checkbox, Categorie_trouble trouble) {
//        ;
//        if (checkbox.isSelected()) {
//            patient.getDossier().ajouterTrouble(trouble);
//            for (Categorie_trouble t : patient.getDossier().getListeTroubles()) {
//                System.out.println("- " + t);
//            }
//        } else {
//            patient.getDossier().supprimerTrouble(trouble);
//            for (Categorie_trouble t : patient.getDossier().getListeTroubles()) {
//                System.out.println("- " + t);
//            }
//        }
//    }
//
//
//}
}