package ESI.TP.Clinic.Modules.RDV;
import ESI.TP.Clinic.Modules.BO.BilanOrthophonique;
import ESI.TP.Clinic.Modules.patient.Patient;

import java.util.*;
import java.time.LocalDateTime;
public class AtelierGroupe extends RendezVous {
    static final long serialVersionUID=1L;

    final private String Thematique ;
    private ArrayList<Integer> numerosDossiers = new ArrayList<Integer> ();
    private BilanOrthophonique BO ;
    public AtelierGroupe(String duree ,String Thematique , ArrayList<Integer> PatientsNumbers,LocalDateTime date) {
        super(duree,date);
        this.Thematique=Thematique;
        for(Integer P: PatientsNumbers ) {
            this.numerosDossiers.add(P);
        }

    }
    // getters
    public String getThematique() {
        return this.Thematique;
    }
    //
    public ArrayList<Integer> getnumerosDossiers(){
        return this.numerosDossiers;
    }
    @Override
    public String getType() {
        return "Atelier de groupe";
    }

}