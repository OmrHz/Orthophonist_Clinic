package ESI.TP.Clinic.Modules.RDV;
import ESI.TP.Clinic.Modules.BO.BilanOrthophonique;
import ESI.TP.Clinic.Modules.patient.Patient;

import java.util.*;
public class AtelierGroupe extends RendezVous {
    final private String Thematique ;
    Set<Integer> Patients = new HashSet<Integer> ();
    private BilanOrthophonique BO ;
    public AtelierGroupe(String duree ,String Thematique , HashSet<Patient> Patients) {
        super(duree);
        this.Thematique=Thematique;
        for(Patient P: Patients ) {
            this.Patients.add(P.getID());
        }

    }
    // getters
    public String getThematique() {
        return this.Thematique;
    }

}
