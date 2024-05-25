package ESI.TP.Clinic.Modules.RDV;

import ESI.TP.Clinic.Modules.BO.BilanOrthophonique;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
public abstract class RendezVous implements Serializable {
    final static long serialVersionUID=1L;
    private  String duree , observation;
    private LocalDateTime date;
    private BilanOrthophonique BO ;
    //le constructeur
    public RendezVous(String duree, LocalDateTime date){
        this.duree=duree;
        this.date=date;
        this.observation=null;

    }
    //getters
    public LocalDateTime getDate() {
        return this.date;
    }
    public String getDuree() {
        return this.duree;
    }
    // setters
    public void setObservation(String observation) {
        this.observation=observation;
    }
    //
    public  String getType() {
        return null;// Abstract method to get the type of appointment
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RendezVous that = (RendezVous) o;
        return Objects.equals(duree, that.duree) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(duree, date);
    }


}


