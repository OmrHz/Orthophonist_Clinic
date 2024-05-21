package ESI.TP.Clinic.Modules.test;
import java.util.*;
public class Exercice {
    private String consigne;
    private String materiel;
    private List<Integer> scores;

    public Exercice(String consigne, String materiel, List<Integer> scores) {
        this.consigne = consigne;
        this.materiel = materiel;
        this.scores = scores;
    }

    public int getMoyenneScores() {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return scores.size() > 0 ? sum / scores.size() : 0;
    }

    // Getters and Setters
    public String getConsigne() {
        return consigne;
    }

    public void setConsigne(String consigne) {
        this.consigne = consigne;
    }

    public String getMateriel() {
        return materiel;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }
}
