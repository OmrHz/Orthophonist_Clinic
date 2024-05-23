package ESI.TP.Clinic.Modules.orthophoniste;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.io.Serializable;
public class Cabinet implements Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<String, String> listeMailPassword = new HashMap<String, String>();
    private static final String FILE_NAME = "Mail_mdp.bin";
    private static final String FILE_CABINET_NAME = "Cabinet.bin";
    private final String name="salsa";
    public Cabinet() {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_CABINET_NAME))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // charger la liste des mdp ou creer la
    private void loadMailPassword() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            listeMailPassword = (HashMap<String, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If the file doesn't exist or is empty, we just start with an empty HashMap
            listeMailPassword = new HashMap<String, String>();
        }
    }

    // sauvegarder la liste dans e fichier
    private void saveMailPassword() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(listeMailPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to register a new email-password entry
    public void sauvegarderMailPassword(String mail, String mdp) {
        // charger la liste deja existante
        loadMailPassword();
        // effectuer des controls
        if (!listeMailPassword.containsKey(mail)) {
            // ajouter le couple mail-mdp
            listeMailPassword.put(mail, mdp);
            // affichage
            showAlert("Email " + mail + " and password added successfully!");
            // sauvegarder la liste dans le fichier
            saveMailPassword();
        } else {
            showAlert("Email " + mail + " already exists.");
        }
    }
    //
    // Method to save the Cabinet object to a file
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_CABINET_NAME))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load a Cabinet object from a file
    public static Cabinet loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_CABINET_NAME))) {
            return (Cabinet) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Cabinet();
        }
    }
    //
    // Method to check if an email exists
    public boolean emailExists(String mail) {
        loadMailPassword();
        return listeMailPassword.containsKey(mail);
    }
    // Method to check if an email and password match
    public boolean authenticate(String email, String password) {
        return emailExists(email) && listeMailPassword.get(email).equals(password);
    }
    //
    private void showAlert(String message) {
        System.out.println("Alert: " + message);
    }
}