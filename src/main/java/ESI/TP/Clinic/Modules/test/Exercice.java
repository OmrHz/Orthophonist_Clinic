package ESI.TP.Clinic.Modules.test;
import java.io.Serializable;
import java.util.*;
public class Exercice implements Serializable {
    private String consigne;
    private String materiel;
    private List<Integer> scores;

    public Exercice(String consigne, String materiel) {
        this.consigne = consigne;
        this.materiel = materiel;
        this.scores = new ArrayList<>();
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

    public void addScores(int scores) {
        this.scores.add(scores);
    }
}
