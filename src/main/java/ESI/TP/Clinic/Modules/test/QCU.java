package ESI.TP.Clinic.Modules.test;

import java.util.*;

public class QCU extends Question{
    private String correctAnswer;
    private List<String> choices;

    public QCU(String text, String correctAnswer, List<String> choices) {
        super(text);
        this.correctAnswer = correctAnswer;
        this.choices = choices;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public List<String> getChoices() {
        return choices;
    }

}
