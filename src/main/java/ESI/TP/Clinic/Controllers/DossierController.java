package ESI.TP.Clinic.Controllers;
import ESI.TP.Clinic.Modules.Other.TableDossiers;
import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import ESI.TP.Clinic.Modules.patient.DossierPatient;
import ESI.TP.Clinic.Modules.patient.Patient;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TableView;


import java.io.IOException;
import java.util.HashMap;

public class DossierController {
    @FXML
    private Text nomPatient;
    @FXML
    private Text lastname;

    public void AccederBilan(ActionEvent event) {
        //go to the bilan page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Bilan.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
        BilanController controller = loader.getController();
        controller.setOrthophoniste(this.orthophoniste);
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Bilan");
        stage.show();
    }

    public class TableRDV {
        private SimpleObjectProperty<String> date;
        private SimpleObjectProperty<String> heure;
        private SimpleObjectProperty<String> type;

        public TableRDV(String date, String heure, String type) {
            this.date = new SimpleObjectProperty<>(date);
            this.heure = new SimpleObjectProperty<>(heure);
            this.type = new SimpleObjectProperty<>(type);
        }

        public String getDate() {
            return date.get();
        }

        public SimpleObjectProperty<String> dateProperty() {
            return date;
        }

        public void setDate(String date) {
            this.date.set(date);
        }

        public String getHeure() {
            return heure.get();
        }

        public SimpleObjectProperty<String> heureProperty() {
            return heure;
        }

        public void setHeure(String heure) {
            this.heure.set(heure);
        }

        public String getType() {
            return type.get();
        }

        public SimpleObjectProperty<String> typeProperty() {
            return type;
        }

        public void setType(String type) {
            this.type.set(type);
        }
    }
    @FXML
    private TableView<TableRDV> TableRDV;

    @FXML
    private TableColumn<TableRDV, String> ColDate;

    @FXML
    private TableColumn<TableRDV, String> ColHeure;

    @FXML
    private TableColumn<TableRDV, String> Type;

    private Orthophoniste orthophoniste;
    private Integer numDossier;
    @FXML
    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
        lastname.setText(" DR "+ orthophoniste.getCompte().getNom());
    }
    @FXML
    private Button RetourButton;
    @FXML
    private Button seDeconnecterButton;
    @FXML
    public void initialize() {
        RetourButton.setOnAction(this::handleRetourButtonAction);
        seDeconnecterButton.setOnAction(this::handleSeDeconnecterButtonAction);
        // Set up the columns in the table
        ColDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        ColHeure.setCellValueFactory(new PropertyValueFactory<>("Heure"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
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
    public void setNumDossier(Integer value) {
        this.numDossier = value;
        ObservableList<TableRDV> table = FXCollections.observableArrayList();
        HashMap<Patient, DossierPatient> patientHashMap = this.orthophoniste.getPatients();
        patientHashMap.entrySet().forEach(entry -> {
            Patient patient = entry.getKey();
            DossierPatient dossier = entry.getValue();
            if (dossier.getNumeroDossier()==numDossier) {
                dossier.getListeRendezVous().forEach(rdv -> {
                    // Assuming TableRDV is the name of the data model class
                    TableRDV tableItem = new TableRDV(rdv.getDate().toString(), rdv.getDuree(), rdv.getType());
                    table.add(tableItem);
                    // set nom patient
                    nomPatient.setText("Dossier de "+ patient.getNom());

                });
                TableRDV.setItems(table);
            }
        });
    }
}
