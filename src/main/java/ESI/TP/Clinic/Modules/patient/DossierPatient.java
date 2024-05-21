package ESI.TP.Clinic.Modules.patient;
import ESI.TP.Clinic.Modules.RDV.RendezVous;
import java.util.*;

public class DossierPatient {
    private int ID;
    // Patient patient ;
    // chaque rendez-vous a un bilan orthophonique , ainsi qu'une fiche de suivi en cas de senace de suivi
    Set<RendezVous> listeRDV = new HashSet<RendezVous>();

    public DossierPatient(Patient patient) {
        this.ID=patient.getID();
        //  this.patient=patient;
    }
    // getters
    public int getId() {
        return this.ID;
    }
}