package ESI.TP.Clinic.Modules.patient;
import ESI.TP.Clinic.Modules.RDV.RendezVous;
import java.util.*;
import java.io.*;
public class DossierPatient implements Serializable {
    final static long serialVersionUID=1;
    private String ID;
    private  int NumeroDossier;
    // Patient patient ;
    // chaque rendez-vous a un bilan orthophonique , ainsi qu'une fiche de suivi en cas de senace de suivi
    private  HashSet<RendezVous> listeRDV = new HashSet<RendezVous>();

    public DossierPatient(Patient patient)  {
        this.ID=patient.getID();
        //  this.patient=patient;

        this.NumeroDossier = loadAndIncrementDossierPatientIncrementer();
    }
    // getters
    public String getId() {
        return this.ID;
    }
    public int getNumeroDossier() {
        return this.NumeroDossier;
    }
    public HashSet<RendezVous> getListeRendezVous(){
        return this.listeRDV;
    }
    // setters
    public void addRdv(RendezVous rdv) {
        this.listeRDV.add(rdv);
    }
    public int hashCode() {
        return ID.hashCode();
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DossierPatient that = (DossierPatient) obj;
        return ID == that.ID;
    }
    //
    public static int loadAndIncrementDossierPatientIncrementer() {
        int lastDossierPatient = -1;
        // Load the integer from the file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("DossierPatientIncrementer.bin"))) {
            lastDossierPatient = (Integer) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If the file does not exist or an error occurs, start with default value 0
            lastDossierPatient = 0;
        }

        // Increment the integer
        lastDossierPatient++;

        // Save the updated integer back to the file
        try (FileOutputStream fileOut = new FileOutputStream("DossierPatientIncrementer.bin");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(lastDossierPatient);
        } catch (IOException i) {

        }

        return lastDossierPatient;
    }

}