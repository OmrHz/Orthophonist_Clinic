package ESI.TP.Clinic.Modules.patient;
import ESI.TP.Clinic.Modules.RDV.RendezVous;
import java.util.*;
import java.io.*;
public class DossierPatient implements Serializable {
    final static long serialVersionUID=1;
    private int ID;
    // Patient patient ;
    // chaque rendez-vous a un bilan orthophonique , ainsi qu'une fiche de suivi en cas de senace de suivi
    public Set<RendezVous> listeRDV = new HashSet<RendezVous>();

    public DossierPatient(Patient patient)  {
        this.ID=patient.getID();
        //  this.patient=patient;
    }
    // getters
    public int getId() {
        return this.ID;
    }

    public int hashCode() {
        return Integer.hashCode(ID);
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DossierPatient that = (DossierPatient) obj;
        return ID == that.ID;
    }
}
