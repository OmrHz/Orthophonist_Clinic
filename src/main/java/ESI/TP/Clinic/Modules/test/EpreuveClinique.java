package ESI.TP.Clinic.Modules.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EpreuveClinique implements Serializable {
    private String nom;
    private List<String> observations;
    private List<Test> tests;

    public EpreuveClinique(String epreuveClinique1) {
        this.nom = epreuveClinique1;
        this.observations = new ArrayList<>();
        this.tests = new ArrayList<>();
    }

    public void addTest(Test test) {
        this.tests.add(test);
    }
    public void addObservation(String observation) {
        this.observations.add(observation);
    }

    public String getNom() {
        return this.nom;
    }

    public List<Test> getTests() {
        return (List<Test>) this.tests;
    }
}
