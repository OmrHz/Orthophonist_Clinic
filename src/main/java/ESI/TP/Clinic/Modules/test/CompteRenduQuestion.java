package ESI.TP.Clinic.Modules.test;

public class CompteRenduQuestion extends CompteRendu{
    private Question question;

    public CompteRenduQuestion(Test test, Question question) {
        super(test);
        this.question = question;
    }

    // Getters and Setters
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
