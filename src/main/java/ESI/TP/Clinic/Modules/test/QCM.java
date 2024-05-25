package ESI.TP.Clinic.Modules.test;
import java.util.*;
public class QCM extends Question{
    private List<String> correctAnswers;
    private List<String> choices;

    public QCM(String text, List<String> correctAnswers, List<String> choices) {
        super(text);
        this.correctAnswers = correctAnswers;
        this.choices = choices;
    }

}
