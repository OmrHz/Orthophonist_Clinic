package ESI.TP.Clinic.Modules.RDV;
import ESI.TP.Clinic.Modules.BO.BilanOrthophonique;
import ESI.TP.Clinic.Modules.patient.Patient;

import java.util.*;
import java.time.LocalDateTime;
public class AtelierGroupe extends RendezVous {
    static final long serialVersionUID=1L;

    final private String Thematique ;
    Set<Integer> Patients = new HashSet<Integer> ();
    private BilanOrthophonique BO ;
    public AtelierGroupe(String duree ,String Thematique , HashSet<Patient> Patients,LocalDateTime date) {
        super(duree,date);
        this.Thematique=Thematique;
        for(Patient P: Patients ) {
            this.Patients.add(P.getID());
        }

    }
    // getters
    public String getThematique() {
        return this.Thematique;
    }
    //
    @Override
    public String getType() {
        return "Atelier de groupe";
    }

}