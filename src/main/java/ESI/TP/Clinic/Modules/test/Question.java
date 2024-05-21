package ESI.TP.Clinic.Modules.test;

public abstract class Question {
    private final String enonce;

    public Question(String enonce) {
        this.enonce = enonce;
    }

    public String getEnonce() {
        return enonce;
    }

    public abstract int getScore();


}
