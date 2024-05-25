package ESI.TP.Clinic.Modules.orthophoniste;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.io.Serializable;
import java.util.*;
public class Cabinet implements Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<String, ArrayList<String>> listeMailPassword = new HashMap<String, ArrayList<String>>();
    private final String name="Cabinet_Salsa";
    //getter
    public String getNomCabinet() {
        return this.name;
    }
    public Cabinet(String FILE_CABINET_NAME) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_CABINET_NAME+".bin"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // charger la liste des mdp ou creer la
    private void loadMailPassword() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mail_password.bin"))) {
            listeMailPassword = (HashMap<String, ArrayList<String>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If the file doesn't exist or is empty, we just start with an empty HashMap
            listeMailPassword = new HashMap<String, ArrayList<String>>();
        }
    }

    // sauvegarder la liste dans e fichier
    private void saveMailPassword() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mail_password.bin"))) {
            oos.writeObject(listeMailPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to register a new email-password entry
    public void sauvegarderMailPassword(String name,String mail, String mdp,String  FILE_NAME) {
        // charger la liste deja existante
        loadMailPassword( );
        // effectuer des controls
        if (!listeMailPassword.containsKey(mail)) {
            // ajouter le couple mail-mdp
            ArrayList<String> array = new ArrayList<String>();
            array.add(name);
            array.add(mdp);
            listeMailPassword.put(mail, array);
            // affichage
            showAlert("Email " + mail + " and password added successfully!");
            // sauvegarder la liste dans le fichier
            saveMailPassword( );
        } else {
            showAlert("Email " + mail + " already exists.");
        }
    }
    //
    // Method to save the Cabinet object to a file
    public void saveToFile(String FILE_CABINET_NAME) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_CABINET_NAME+".bin"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load a Cabinet object from a file
    public static Cabinet loadFromFile(String FILE_CABINET_NAME) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_CABINET_NAME+".bin"))) {
            return (Cabinet) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Cabinet(FILE_CABINET_NAME);
        }
    }
    //
    // Method to check if an email exists
    public boolean emailExists(String mail,String  FILE_NAME) {
        loadMailPassword();
        return listeMailPassword.containsKey(mail);
    }
    // Method to check if an email and password match
    public boolean authenticate(String email, String password,String  FILE_NAME) {
        return emailExists(email, FILE_NAME) && listeMailPassword.get(email).get(1).equals(password);
    }
    //
    private void showAlert(String message) {
        System.out.println("Alert: " + message);
    }
    //getters
    public HashMap<String, ArrayList<String>> getListeMailPassword() {
        return this.listeMailPassword;
    }
}