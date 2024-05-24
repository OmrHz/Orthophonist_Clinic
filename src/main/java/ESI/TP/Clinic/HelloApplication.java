package ESI.TP.Clinic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class HelloApplication extends Application
{
    @Override
        public void start(Stage primaryStage) throws Exception {
//        Orthophoniste ort = new Orthophoniste();
//        ort.copyFrom(Orthophoniste.loadOrthophonisteFromFile("Hamza"));
//        LocalDate date = LocalDate.parse("1960-05-22");
//        Patient Adulte1 = new Adulte("Bencherif","Mohamed","Blida",date,"alger","PrstProba","Pr","0798564735");
//        date = LocalDate.parse("2016-06-02");
//        Patient Enfant1 = new Enfant("Benlimam","Amal","El-Oued",date,"Constantine","3emePrimaire","0564852907","0734987654");
//        //prise en charge et programmer consultation
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//        LocalDateTime dateTime = LocalDateTime.parse("22/05/2024 10:00", formatter);
//        ort.priseEnCharge(Enfant1);
//        ort.priseEnCharge(Adulte1);
//        ort.ProgrammerConsultation(dateTime, Enfant1);
//        dateTime = LocalDateTime.parse("02/09/2024 15:00", formatter);
//        ort.priseEnCharge(Adulte1);
//        //  ort.ProgrammerConsultation(dateTime, Enfant1);
//        dateTime = LocalDateTime.parse("22/10/2024 10:00", formatter);
//        for (Map.Entry<LocalDateTime, RendezVous> entry : ort.getAgenda().getAgenda().entrySet())
//            System.out.println(entry.getKey().get(null)+"  "+ entry.getValue().getDuree());
//        //
//        Orthophoniste.SaveOrthophonisteFromFile(ort.getCompte().getNom(), ort);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/SignUP.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Add Orthophonist");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

        public static void main(String[] args) {
            launch(args);
        }
}