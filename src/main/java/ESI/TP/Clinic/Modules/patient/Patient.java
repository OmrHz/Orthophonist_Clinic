package ESI.TP.Clinic.Modules.patient;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.io.*;
import java.util.Objects;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Patient implements Serializable {

    // les attribus
    static final long serialVersionUID = 1;

    private String ID;
    private String Nom, Prenom, Adresse, LieuNaissance;
    private LocalDate DateNaissance;
    private boolean prisEnCharge = false;

    // le constructeur
    public Patient() {

    }

    public Patient(String Nom, String Prenom, String Adresse, LocalDate DateNaissance, String LieuNaissance) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Adresse = Adresse;
        this.LieuNaissance = LieuNaissance;
        this.ID = generateID(Nom, Prenom, DateNaissance);
        this.DateNaissance = DateNaissance;
    }

    private String generateID(String nom, String prenom, LocalDate dateNaissance) {
        String input = nom + prenom + dateNaissance.toString();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // setters
    public void setPrisEnCharge() {
        this.prisEnCharge = true;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public void setLieuNaissance(String LieuNaissance) {
        this.LieuNaissance = LieuNaissance;
    }

    //getters
    public String getNom() {
        return this.Nom;
    }

    public String getPrenom() {
        return this.Prenom;
    }

    public String getAdresse() {
        return this.Adresse;
    }

    public LocalDate getDateNaissance() {
        return this.DateNaissance;
    }

    public String getLieuNaissance() {
        return this.LieuNaissance;
    }

    public String getID() {
        return this.ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return ID.equals(patient.ID) &&
                Objects.equals(Nom, patient.Nom) &&
                Objects.equals(Prenom, patient.Prenom) &&
                Objects.equals(DateNaissance, patient.DateNaissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, Nom, Prenom, DateNaissance);
    }

    // calculer l'age
    public int calculerAge() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        return (year - DateNaissance.getYear());
    }

    public boolean getPrisEnCharge() {
        return this.prisEnCharge;
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Patient_" + this.getNom() + "_" + this.getID() + ".bin"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Patient loadFromFile(String ID) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Patient_" + ID + ".bin"))) {
            return (Patient) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}



