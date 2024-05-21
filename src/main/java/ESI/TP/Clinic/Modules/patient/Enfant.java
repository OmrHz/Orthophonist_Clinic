package ESI.TP.Clinic.Modules.patient;

import java.time.LocalDateTime;
public class Enfant extends Patient {

    private String ClasseEtude;
    private String NumeroMere;
    private String NumeroPere;
    // le constructeur
    public Enfant(String Nom , String Prenom , String Adresse, LocalDateTime DateNaissance,String LieuNaissance, String ClasseEtude , String NumeroMere , String NumeroPere) {
        super(Nom,Prenom,Adresse,DateNaissance,LieuNaissance);
        this.ClasseEtude=ClasseEtude;
        this.NumeroMere=NumeroMere;
        this.NumeroPere=NumeroPere;
    }
    // setters
    public void setClasseEtude(String ClasseEtude) {
        this.ClasseEtude=ClasseEtude ;
    }
    public void setNumeroMere (String NumeroMere ) {
        this.NumeroMere = NumeroMere ;
    }
    public void setNumeroPere(String NumeroPere) {
        this.NumeroPere=NumeroPere;
    }
    //getters
    public String getClasseEtude() {
        return this.ClasseEtude ;
    }
    public String getNumeroMere ( ) {
        return this.NumeroMere  ;
    }
    public String getNumeroPere() {
        return this.NumeroPere;
    }




}

