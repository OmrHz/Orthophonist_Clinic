package ESI.TP.Clinic.Modules.RDV;


import ESI.TP.Clinic.Modules.BO.PremierBilanOrthophonique;

public class Consultation extends RendezVous {
    private String Nom , Prenom ;
    private int Age ;
    private PremierBilanOrthophonique BO ;
    public Consultation(String duree ,String Nom , String Prenom , int Age ) {
        super(duree);
        this.Nom=Nom;
        this.Prenom=Prenom;
        this.Age=Age;
    }
    //setters
    public void setNom(String Nom) {
        this.Nom=Nom;
    }
    public void setPreom(String Prenom) {
        this.Prenom=Prenom;
    }
    public void setAge(int Age) {
        this.Age=Age;
    }
    //getters
    public String getNom() {
        return this.Nom;
    }
    public String getPrenom() {
        return this.Prenom;
    }
    public int getAge() {
        return this.Age;
    }
}
