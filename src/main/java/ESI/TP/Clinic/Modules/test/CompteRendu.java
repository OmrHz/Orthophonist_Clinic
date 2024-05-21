package ESI.TP.Clinic.Modules.test;

public abstract class CompteRendu {
    private Test test;

    public CompteRendu(Test test) {
        this.test = test;
    }

    // Getters and Setters
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
