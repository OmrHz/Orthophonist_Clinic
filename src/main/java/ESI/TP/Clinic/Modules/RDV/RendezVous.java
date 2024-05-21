package ESI.TP.Clinic.Modules.RDV;

import ESI.TP.Clinic.Modules.BO.BilanOrthophonique;

public abstract class RendezVous {
    String duree , observation;
    private BilanOrthophonique BO ;
    //le constructeur
    public RendezVous(String duree){
        this.duree=duree;
        this.observation=null;

    }
    //getters

    public String getDuree() {
        return this.duree;
    }
    // setters
    public void setObservation(String observation) {
        this.observation=observation;
    }



}