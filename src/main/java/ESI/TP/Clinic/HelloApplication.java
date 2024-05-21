package ESI.TP.Clinic;

import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.*;
public class HelloApplication
{
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("salsa.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 620, 620);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
public static void main( String [] args) {
    Orthophoniste o = new Orthophoniste();
    // Serialize the object
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("orthophoniste.bin"))) {
        oos.writeObject(o);
        System.out.println("Serialization successful: " + o);
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Deserialize the object
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("orthophoniste.bin"))) {
        Orthophoniste oo = ( Orthophoniste)ois.readObject();
        System.out.println("Deserialization successful: " + oo);
        System.out.println(((Orthophoniste)oo).Compte.getNom());
        System.out.println(((Orthophoniste)oo).Compte.getPrenom());
        System.out.println(((Orthophoniste)oo).Compte.getAdresse());
        System.out.println(((Orthophoniste)oo).Compte.getMotDePasse());
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    finally {

    }
}
}