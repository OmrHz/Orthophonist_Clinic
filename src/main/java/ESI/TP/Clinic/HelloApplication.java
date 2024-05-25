package ESI.TP.Clinic;

import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import ESI.TP.Clinic.Modules.patient.Adulte;
import ESI.TP.Clinic.Modules.patient.Enfant;
import ESI.TP.Clinic.Modules.patient.Patient;
import ESI.TP.Clinic.Modules.test.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static javafx.application.Application.launch;

//public class HelloApplication extends Application
//{
//    @Override
//        public void start(Stage primaryStage) throws Exception {
//        Orthophoniste ort = new Orthophoniste("chattah","salsabila","blida","0697174425","ms_chattah@esi.dz","1234");
//
//        //Orthophoniste ort = new Orthophoniste();
//        //ort.copyFrom(Orthophoniste.loadOrthophonisteFromFile("chattah"));
//        LocalDate date = LocalDate.parse("1960-05-22");
//        Patient Adulte1 = new Adulte("Bencherif","Mohamed","Blida",date,"alger","PrstProba","Pr","0798564735");
//        date = LocalDate.parse("2016-06-02");
//        Patient Enfant1 = new Enfant("Benlimam","Amal","El-Oued",date,"Constantine","3emePrimaire","0564852907","0734987654");
//        //prise en charge et programmer consultation
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//        LocalDateTime dateTime = LocalDateTime.parse("22/05/2024 10:00", formatter);
//        ort.priseEnCharge(Enfant1);
//        ort.ProgrammerConsultation(dateTime, Enfant1);
//        dateTime = LocalDateTime.parse("02/09/2024 15:00", formatter);
//        ort.priseEnCharge(Adulte1);
//        ort.ProgrammerConsultation(dateTime, Enfant1);
//        dateTime = LocalDateTime.parse("22/10/2024 10:00", formatter);
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/SignUP.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        primaryStage.setTitle("Add Orthophonist");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//        public static void main(String[] args) {
//            launch(args);
//        }
//}

 public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Hello.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Rendez-vous");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
 }





//public class HelloApplication {
//    public static void main(String[] args) {
//        EpreuveClinique objectToSerialize = new EpreuveClinique("EpreuveClinique1");
//        //list of questions
//        Question QCU1 = new QCU("QCU1", "B", Arrays.asList("A", "B", "C", "D", "E"));
//        Question QCU2 = new QCU("QCU2", "A", Arrays.asList("A", "B", "C", "D", "E"));
//        Question QCU3 = new QCU("QCU3", "C", Arrays.asList("A", "B", "C", "D", "E"));
//
//        try {
//            FileOutputStream fileOut = new FileOutputStream("object.bin");
//            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
//            objectOut.writeObject(objectToSerialize);
//            objectOut.close();
//            fileOut.close();
//            System.out.println("Object serialized successfully.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        EpreuveClinique E = null;
//
//    }
//}

