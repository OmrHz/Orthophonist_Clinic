package ESI.TP.Clinic.Modules.patient;
import java.time.LocalDateTime;
import java.time.LocalDate;

import java.util.Scanner;
public abstract class Patient {
    // les attribus
    static private int cptID=1;
    private int ID;
    private String Nom,Prenom,Adresse,LieuNaissance;
    private LocalDateTime DateNaissance;
    private boolean prisEnCharge = false;
    // le constructeur
    public Patient(String Nom , String Prenom , String Adresse, LocalDateTime DateNaissance,String LieuNaissance ) {
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
    public LocalDateTime getDateNaissance() {
        return this.DateNaissance;
    }
    public String getLieuNaissance() {
        return this.LieuNaissance;
    }
    public int getID() {
        return this.ID;
    }
    //redefinir la methode hashcode
    public int hashCode() {
        return this.Nom.hashCode();
    }
    //redefinir la methode equals
    public boolean equals(Object o) {
        return (this.Nom == ((Patient)o).Nom);
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

}


