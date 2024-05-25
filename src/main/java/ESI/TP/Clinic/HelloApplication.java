package ESI.TP.Clinic;

import ESI.TP.Clinic.Modules.orthophoniste.Cabinet;
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
public class HelloApplication extends Application {
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Hello.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Add Orthophonist");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // le cabinet
    // Cabinet cabinet = new Cabinet("Cabinet_Salsa");
     /*  // creer un orthophoniste

     // les patients
      * */


//    for (Map.Entry<Patient, DossierPatient> entry : patients.entrySet()) {
//        Patient patient = entry.getKey();
//        DossierPatient dossier = entry.getValue();
//        System.out.println("Patient: " + patient.getNom() + " " + patient.getPrenom());
//        // Display patient details
//        if (patient instanceof Adulte) {
//            Adulte adulte = (Adulte) patient;
//            System.out.println("Type: Adulte");
//            System.out.println("Adresse: " + adulte.getAdresse());
//            System.out.println("Diplome: " + adulte.getDiplome());
//            System.out.println("Profession: " + adulte.getProfession());
//            System.out.println("NumeroTelephone: " + adulte.getNumeroTelephone());
//        } else if (patient instanceof Enfant) {
//            Enfant enfant = (Enfant) patient;
//            System.out.println("Type: Enfant");
//            System.out.println("Adresse: " + enfant.getAdresse());
//            System.out.println("ClasseEtude: " + enfant.getClasseEtude());
//            System.out.println("NumeroMere: " + enfant.getNumeroMere());
//            System.out.println("NumeroPere: " + enfant.getNumeroPere());
//        }
//        // Display dossier details (customize this according to DossierPatient properties)
//        System.out.println("Dossier details: " + dossier.toString());
//        System.out.println();
//    }
//     ort.ProgrammerConsultation(dateTime, Enfant1);
//     dateTime = LocalDateTime.parse("02/09/2024 15:00", formatter);
//     ort.priseEnCharge(Adulte1);
//     ort.ProgrammerConsultation(dateTime, Adulte1);




    public static void main(String[] args) {
        launch(args);
    }
}



// public class HelloApplication extends Application {
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Hello.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        primaryStage.setTitle("Rendez-vous");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//    public static void main(String[] args) {
//        launch(args);
//    }
// }





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

