package ESI.TP.Clinic.Modules.test;
import java.io.Serializable;
import java.util.*;
// Class Abstract
public abstract class Test implements Serializable {
    private String nom;
    private int capacite;
    private String Conclusion;
    public Test(String nom, int capacite) {
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

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    public String getConclusion() {
        return Conclusion;
    }

    public void setConclusion(String conclusion) {
        Conclusion = conclusion;
    }
}
