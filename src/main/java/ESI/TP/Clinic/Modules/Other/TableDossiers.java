package ESI.TP.Clinic.Modules.Other;

public class TableDossiers{
    public  String Nom;
    public String Prenom;
    public Integer Age;
    public  Integer Number;
    public TableDossiers( String Nom, String Prenom, Integer Age, Integer Numbre){
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Age = Age;
        this.Number = Numbre;
    }
    // Getters
    public String getNom() {
        return Nom;
    }
    public String getPrenom() {
        return Prenom;
    }
    public Integer getAge() {
        return Age;
    }
    public Integer getNumber() {
        return Number;
    }
    // Setters
    public void setNom(String Nom) {
        this.Nom = Nom;
    }
    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }
    public void setAge(Integer Age) {
        this.Age = Age;
    }
    public void setNumber(Integer Number) {
        this.Number = Number;
    }

}