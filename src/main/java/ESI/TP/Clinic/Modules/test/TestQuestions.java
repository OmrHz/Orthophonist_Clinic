package ESI.TP.Clinic.Modules.test;
import java.util.*;
public class TestQuestions extends Test{
    private List<Question> questions;
    public TestQuestions(String nom, String capacite, List<Question> questions) {
        super(nom, capacite);
        this.questions = questions;
    }
    @Override
    public int calculerScoreTotal() {
        int total = 0;
        for (Question question : questions) {
            total += question.getScore();
        }
        return total;
    }
    // Getters and Setters
    public List<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
