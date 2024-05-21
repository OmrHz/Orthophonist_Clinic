package ESI.TP.Clinic.Modules.test;
import java.util.*;
// Class Abstract
public abstract class Test {
    private String nom;
    private String capacite;

    public Test(String nom, String capacite) {
        this.nom = nom;
        this.capacite = capacite;
    }

    public abstract int calculerScoreTotal();

    // Getters and Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }
}
