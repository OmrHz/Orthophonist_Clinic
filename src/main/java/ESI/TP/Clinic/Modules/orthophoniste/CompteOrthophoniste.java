package ESI.TP.Clinic.Modules.orthophoniste;

import java.io.Serializable;
public class CompteOrthophoniste implements Serializable{
    private static final long serialVersionUID = 1L;

    private String Nom, Prenom ,Adresse , NumeroTelephone , AdresseEMail ,MotDePasse;

    // le constructeur
    public CompteOrthophoniste(String Nom, String Prenom ,String Adresse , String NumeroTelephone ,String AdresseEMail , String MotDePasse) {
        this.Nom=Nom;
        this.Prenom=Prenom;
        this.Adresse=Adresse;
        this.NumeroTelephone=NumeroTelephone;
        this.AdresseEMail=AdresseEMail;
        this.MotDePasse=MotDePasse;
    }
    // Getters
    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getNumeroTelephone() {
        return NumeroTelephone;
    }

    public String getAdresseEMail() {
        return AdresseEMail;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }

    // Setters
    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public void setNumeroTelephone(String NumeroTelephone) {
        this.NumeroTelephone = NumeroTelephone;
    }

    public void setAdresseEMail(String AdresseEMail) {
        this.AdresseEMail = AdresseEMail;
    }

    public void setMotDePasse(String MotDePasse) {
        this.MotDePasse = MotDePasse;
    }


    public void setPhone(String text) {
        this.NumeroTelephone = text;
    }

    public void setEmail(String text) {
        this.AdresseEMail = text;
    }

    public void setPassword(String text) {
        this.MotDePasse = text;
    }
}
