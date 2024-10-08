package ESI.TP.Clinic.Modules.RDV;
import ESI.TP.Clinic.Modules.Fiche.FicheDeSuivi;
import java.time.LocalDateTime;

public class SeanceSuivi extends RendezVous {
    final static long serialVersionUID=1l;
    private int NumeroDossier;
    private typeSeanceSuivi type;
    private FicheDeSuivi fiche;
    public SeanceSuivi(String duree,int NumeroDossier,typeSeanceSuivi Type, LocalDateTime date) {
        super(duree,date);
        this.NumeroDossier=NumeroDossier;
        this.type=Type;
    }
    //setters
    public void setNumeroDossier(int NumeroDossier) {
        this.NumeroDossier=NumeroDossier;
    }
    public void setType(typeSeanceSuivi type) {
        this.type=type;
    }
    public void setFicheDesuivi(FicheDeSuivi fiche) {
        this.fiche=fiche;
    }
    //getters
    public int getNumeroDossier() {
        return this.NumeroDossier;
    }
    public typeSeanceSuivi getTypee() {
        return this.type;
    }
    public FicheDeSuivi getFicheDeSuivi() {
        return this.fiche;
    }
    //
    @Override
    public String getType() {
        return "SeanceDeSuivi";
    }

}