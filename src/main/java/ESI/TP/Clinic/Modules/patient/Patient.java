package ESI.TP.Clinic.Modules.patient;
public abstract class Patient {
    // les attribus
    private String Nom,Prenom,Adresse,DateNaissance,LieuNaissance;
    // le constructeur
    public Patient(String Nom , String Prenom , String Adresse, String DateNaissance,String LieuNaissance ) {
        this.Nom=Nom;
        this.Prenom=Prenom;
        this.Adresse=Adresse;
        this.DateNaissance= DateNaissance;
        this.LieuNaissance= LieuNaissance;
    }
    // setters
    public void setNom(String Nom) {
        this.Nom=Nom;
    }
    public void setPrenom(String Prenom) {
        this.Prenom=Prenom;
    }
    public void setAdresse(String Adresse) {
        this.Adresse=Adresse;
    }
    public void setDateNaissance(String DateNaissance) {
        this.DateNaissance=DateNaissance;
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
    public String getDateNaissance() {
        return this.DateNaissance;
    }
    public String getLieuNaissance() {
        return this.LieuNaissance;
    }
    //redefinir la methode hashcode
    public int hashCode() {
        return this.Nom.hashCode();
    }
    //redefinir la methode equals
    public boolean equals(Object o) {
        return (this.Nom == ((Patient)o).Nom);
    }


}

