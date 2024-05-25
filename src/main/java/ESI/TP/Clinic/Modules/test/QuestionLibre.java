package ESI.TP.Clinic.Modules.test;

public class QuestionLibre extends Question{
    private String answer;

    public QuestionLibre(String enonce, String answer) {
        super(enonce);
        this.answer = answer;
    }
}
