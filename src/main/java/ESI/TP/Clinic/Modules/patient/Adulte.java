package ESI.TP.Clinic.Modules.patient;

import java.time.LocalDate;

public class Adulte extends Patient{
    private String Diplome ;
    private String Profession ;
    private String NumeroTelephone;
    public Adulte(String Nom , String Prenom , String Adresse, LocalDate DateNaissance,String LieuNaissance, String Diplome , String Profession , String NumeroTelephone) {
        super(Nom,Prenom,Adresse,DateNaissance,LieuNaissance);
        this.Diplome= Diplome;
        this.Profession=Profession;
        this.NumeroTelephone=NumeroTelephone;
    }
    public Adulte () {

    }
    // setters
    public void setProfession(String Profession) {
        this.Profession= Profession ;
    }
    public void setDiplome (String Diplome ) {
        this.Diplome = Diplome ;
    }
    public void setNumeroTelephone(String NumeroTelephone) {
        this.NumeroTelephone=NumeroTelephone;
    }
    //getters
    public String getProfession() {
        return this.Profession ;
    }
    public String getDiplome ( ) {
        return this.Diplome ;
    }
    public String getNumeroTelephone() {
        return this.NumeroTelephone;
    }
}