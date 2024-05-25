package ESI.TP.Clinic.Modules.patient;
import java.time.LocalDateTime;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.io.*;
import java.util.Objects;

public abstract class Patient implements Serializable{
    // les attribus
    static final long serialVersionUID=1;
    static private int cptID=1;
    private int ID;
    private String Nom,Prenom,Adresse,LieuNaissance;
    private LocalDate DateNaissance;
    private boolean prisEnCharge = false;
    // le constructeur
    public Patient() {

    }
    public Patient(String Nom , String Prenom , String Adresse, LocalDate DateNaissance,String LieuNaissance ) {
        this.Nom=Nom;
        this.Prenom=Prenom;
        this.Adresse=Adresse;
//		this.DateNaissance= DateNaissance;
        this.LieuNaissance= LieuNaissance;
        this.ID=cptID;
//		 Scanner scanner = new Scanner(System.in);
//	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	     LocalDateTime appointmentDateTime = LocalDateTime.parse(dateTimeString, formatter);
//	     scanner.close();
        this.DateNaissance=DateNaissance;
        cptID++;
    }

    // setters
    public void setPrisEnCharge() {
        this.prisEnCharge=true;
    }
    public void setNom(String Nom) {
        this.Nom=Nom;
    }
    public void setPrenom(String Prenom) {
        this.Prenom=Prenom;
    }
    public void setAdresse(String Adresse) {
        this.Adresse=Adresse;
    }

    public void setLieuNaissance(String LieuNaissance) {
        this.LieuNaissance=LieuNaissance;
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
    public int getID() {
        return this.ID;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return ID == patient.ID &&
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
        LocalDate currentDate = LocalDate.now()   ;
        int year = currentDate.getYear();
        return (year- DateNaissance.getYear());
    }
    public boolean getPrisEnCharge() {
        return this.prisEnCharge;
    }
    //
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Patient_"+this.getID()+".bin"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load a Cabinet object from a file
    public static Patient loadFromFile(int ID) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Patient_"+ID+".bin"))) {
            return (Patient) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    //



}



