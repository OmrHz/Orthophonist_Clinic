package ESI.TP.Clinic.Modules.test;

public class CompteRenduExercice extends CompteRendu{
    private Exercice exercice;

    public CompteRenduExercice(Test test, Exercice exercice) {
        super(test);
        this.exercice = exercice;
    }

    // Getters and Setters
    public Exercice getExercice() {
        return exercice;
    }

    public void setExercice(Exercice exercice) {
        this.exercice = exercice;
    }
}
