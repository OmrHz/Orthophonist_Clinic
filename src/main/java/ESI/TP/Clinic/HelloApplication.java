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

