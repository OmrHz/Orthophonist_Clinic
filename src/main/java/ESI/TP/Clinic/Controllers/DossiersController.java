package ESI.TP.Clinic.Controllers;
import ESI.TP.Clinic.Modules.Other.TableDossiers;
import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import ESI.TP.Clinic.Modules.patient.DossierPatient;
import ESI.TP.Clinic.Modules.patient.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.util.HashMap;

public class DossiersController {

    @FXML
    private TableView<TableDossiers> tableView;
    @FXML
    private TableColumn<TableDossiers,String> nomColumn;
    @FXML
    private TableColumn<TableDossiers,String> prenomColumn;
    @FXML
    private TableColumn<TableDossiers,Integer>  ageColumn;
    @FXML
    private TableColumn<TableDossiers,Integer>  numDossierColumn;
    private Orthophoniste orthophoniste;
    @FXML
    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
        populateTableView();
    }
    @FXML
    private Button RetourButton;
    @FXML
    private Button seDeconnecterButton;
    @FXML
    public void initialize() {
        RetourButton.setOnAction(this::handleRetourButtonAction);
        seDeconnecterButton.setOnAction(this::handleSeDeconnecterButtonAction);
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
        numDossierColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
    }
    @FXML
    public void populateTableView() {
        // Set up the columns in the table

        if (nomColumn != null && prenomColumn != null && ageColumn != null && numDossierColumn != null && tableView != null && orthophoniste != null) {
            System.out.println("Populating TableView");
            ObservableList<TableDossiers> table = FXCollections.observableArrayList();
            HashMap<Patient, DossierPatient> patientHashMap = this.orthophoniste.getPatients();
            patientHashMap.entrySet().forEach(entry -> {
                Patient patient = entry.getKey();
                DossierPatient dossier = entry.getValue();
//                System.out.println(patient.getNom()+" "+ patient.getPrenom()+" "+ patient.calculerAge() +" "+ dossier.getNumeroDossier());
                TableDossiers T = new TableDossiers(patient.getNom(), patient.getPrenom(), patient.calculerAge(), dossier.getNumeroDossier());
               // display the T in the tableview

                table.add(T);
            });
            tableView.setItems(table);
        } else {
            System.err.println("FXML elements are not fully initialized or orthophoniste is null.");
        }
    }
    @FXML
    public void HandleAjouterPatient(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Patient.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
        PatientControllers controller = loader.getController();
        controller.setOrthophoniste(orthophoniste);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("Patient Details");
        newStage.show();

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

    public void HandleAcceder(ActionEvent event) {

    }
}
