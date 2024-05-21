package ESI.TP.Clinic.Modules.test;
import java.util.*;
public class TestExercices extends Test{
    private List<Exercice> exercices;
    public TestExercices(String nom, String capacite, List<Exercice> exercices) {
        super(nom, capacite);
        this.exercices = exercices;
    }

    @Override
    public int calculerScoreTotal() {
        int total = 0;
        for (Exercice exercice : exercices) {
            total += exercice.getMoyenneScores();
        }
        return total;
    }

    // Getters and Setters
    public List<Exercice> getExercices() {
        return exercices;
    }

    public void setExercices(List<Exercice> exercices) {
        this.exercices = exercices;
    }
}
