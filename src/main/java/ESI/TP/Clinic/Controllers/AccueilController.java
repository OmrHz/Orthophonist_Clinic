package ESI.TP.Clinic.Controllers;

import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AccueilController {
    @FXML
    private Pane topPane;

    @FXML
    private Text centeredText;

    @FXML
    private Button nouveauRendezVousButton;

    @FXML
    private Button dossiersPatientsButton;

    @FXML
    private Button statistiquesButton;

    @FXML
    private Button parametresButton;

    @FXML
    private Button gererTestsAnamnesesButton;

    @FXML
    private Button agendaButton;

    @FXML
    private Button seDeconnecterButton;
    private Orthophoniste ort;
    @FXML
    private Text fullname;
    public void setOrthophoniste(Orthophoniste ort) {
        this.ort = ort;
        if(ort !=null ){
            this.fullname.setText("DR " + ort.getCompte().getNom() + " " + ort.getCompte().getPrenom());
        }else{
            this.fullname.setText("DR " + " " + " ");
            System.out.println(" orthophoniste not found in Accueil");
        }
    }
    @FXML
    public void initialize() {
        // Set the event handlers for the buttons
        nouveauRendezVousButton.setOnAction(this::handleNouveauRendezVousButtonAction);
        dossiersPatientsButton.setOnAction(this::handleDossiersPatientsButtonAction);
        statistiquesButton.setOnAction(this::handleStatistiquesButtonAction);
        parametresButton.setOnAction(this::handleParametresButtonAction);
        gererTestsAnamnesesButton.setOnAction(this::handleGererTestsAnamnesesButtonAction);
        agendaButton.setOnAction(this::handleAgendaButtonAction);
        seDeconnecterButton.setOnAction(this::handleSeDeconnecterButtonAction);
    }

    @FXML
    private void handleNouveauRendezVousButtonAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Rendez-vous.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
            TypeRdvController controller = loader.getController();
            controller.setOrthophoniste(ort);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }

        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Nouveau Rendez-vous");
        stage.show();
    }
    // Add other button handlers here

    @FXML
    private void handleDossiersPatientsButtonAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Dossiers.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
        DossiersController controller = loader.getController();
        controller.setOrthophoniste(ort);
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dossiers Patients");
        stage.show();
    }

    @FXML
    private void handleStatistiquesButtonAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Stat.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
        StatisticsController controller = loader.getController();
        controller.setOrthophoniste(ort);
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Accueil");
        stage.show();
    }

    @FXML
    private void handleParametresButtonAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Param.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
        ParametresController controller = loader.getController();
        controller.setOrthophoniste(ort);
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Accueil");
        stage.show();
    }

    @FXML
    private void handleGererTestsAnamnesesButtonAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Epreuves.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
        EpreuvesController controller = loader.getController();
        controller.setOrthophoniste(ort);
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Accueil");
        stage.show();
    }

    @FXML
    private void handleAgendaButtonAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Agenda.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }
        AgendaControllers controller = loader.getController();
        controller.setAgenda(ort.getAgenda());
        Stage newStage = new Stage();
        newStage.setTitle("Agenda");
        newStage.setScene(scene);
        newStage.show();

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
}
