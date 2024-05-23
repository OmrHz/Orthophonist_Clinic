package ESI.TP.Clinic;

import ESI.TP.Clinic.Modules.orthophoniste.Cabinet;
import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ESI.TP.Clinic.Controllers.SignUp;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.*;

import static javafx.application.Application.launch;

public class HelloApplication extends Application
{
    @Override
        public void start(Stage primaryStage) throws Exception {
            Cabinet cabinet = new Cabinet();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUP.fxml"));
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