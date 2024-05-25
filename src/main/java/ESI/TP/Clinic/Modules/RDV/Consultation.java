package ESI.TP.Clinic.Modules.RDV;
import ESI.TP.Clinic.Modules.BO.PremierBilanOrthophonique;
import java.time.LocalDateTime;
public class Consultation extends RendezVous {
    static final long serialVersionUID=1;

    private String Nom , Prenom ;
    private int Age ;
    private PremierBilanOrthophonique BO ;
    public Consultation(String duree ,String Nom , String Prenom , int Age, LocalDateTime date ) {
        super(duree,date);
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
    //
    @Override
    public String getType() {
        return "Consultation";
    }
}

