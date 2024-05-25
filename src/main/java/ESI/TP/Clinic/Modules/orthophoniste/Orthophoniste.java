package ESI.TP.Clinic.Modules.orthophoniste;
import ESI.TP.Clinic.Modules.Fiche.FicheDeSuivi;
import ESI.TP.Clinic.Modules.RDV.*;
import ESI.TP.Clinic.Modules.patient.DossierPatient;
import ESI.TP.Clinic.Modules.patient.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Orthophoniste implements Serializable {
    private static final long serialVersionUID = 1L;
    public class Agenda implements Serializable {
        private static final long serialVersionUID = 1L;
        private TreeMap<LocalDateTime, RendezVous> agenda;

        public Agenda() {
            this.agenda = new TreeMap<>();
        }

        public TreeMap<LocalDateTime, RendezVous> getAgenda() {
            return agenda;
        }

        public void ajouterRendezVous(LocalDateTime date, RendezVous rdv) throws AjoutRdvException {
            System.out.println("piroblim");


            if (agenda.isEmpty()) {
                agenda.put(date, rdv);
                return;
            }

            LocalDateTime previousRDV = agenda.lowerKey(date);
            if (previousRDV != null && previousRDV.getYear() == date.getYear() &&
                    previousRDV.getMonthValue() == date.getMonthValue() &&
                    previousRDV.getDayOfMonth() == date.getDayOfMonth()) {
                LocalDateTime nextRDV = agenda.higherKey(date);

                if (date.getHour() - previousRDV.getHour() < 3 && nextRDV != null &&
                        nextRDV.getHour() - date.getHour() < 3)
                    throw new AjoutRdvException("Creux insuffisant ");
            }

            agenda.put(date, rdv);
        }

//        public void afficherAgenda() {
//            for (Map.Entry<LocalDateTime, RendezVous> entry : agenda.entrySet()) {
//                LocalDateTime key = entry.getKey();
//                RendezVous value = entry.getValue();
//                if (value instanceof AtelierGroupe) {
//                    System.out.println("Date: " + key + ", Thematique: " + ((AtelierGroupe) value).getThematique() + ", Duree: " + ((AtelierGroupe) value).duree);
//                } else if (value instanceof Consultation) {
//                    System.out.println("Date: " + key + ", Nom: " + ((Consultation) value).getNom() + ", Duree: " + ((Consultation) value).duree);
//                } else if (value instanceof SeanceSuivi) {
//                    System.out.println("Date: " + key + ", Numero dossier: " + ((SeanceSuivi) value).getNumeroDossier() + ", Duree: " + ((SeanceSuivi) value).duree);
//                }
//            }
//        }
    }
    private CompteOrthophoniste Compte;
    private Agenda agenda;
    private HashMap<Patient, DossierPatient> Patients = new HashMap<>();

    public CompteOrthophoniste getCompte() {
        return Compte;
    }

    public void setCompte(CompteOrthophoniste compte) {
        if (compte != null) {
            this.Compte = compte;
        }
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        if (agenda != null) {
            this.agenda = agenda;
        }
    }

    public HashMap<Patient, DossierPatient> getPatients() {
        return this.Patients;
    }

    public void addPatients(Patient P, DossierPatient d) {
        if (P != null && d != null) {
            this.Patients.put(P, d);
        }
    }

//    public Orthophoniste(CompteOrthophoniste Compte, Agenda agenda) {
//        this.Compte = Compte;
//        this.agenda = agenda;
//    }

    public Orthophoniste(String Nom, String Prenom, String Adresse, String NumeroTelephone, String Email, String MotDePasse) {
        this.Compte = new CompteOrthophoniste(Nom, Prenom, Adresse, NumeroTelephone, Email, MotDePasse);
        this.agenda= new Agenda();
        this.Patients= new HashMap<Patient,DossierPatient>();
        SaveOrthophonisteFromFile(Nom,this);
    }

    public Orthophoniste() {

    }

    public void priseEnCharge(Patient patient) {
        if (patient != null && !patient.getPrisEnCharge() && !this.getPatients().containsKey(patient)) {
            DossierPatient d = new DossierPatient(patient);
            patient.setPrisEnCharge();
            this.addPatients(patient, d);
            patient.saveToFile();
        }
    }

    public void copyFrom(Orthophoniste other) {
        this.Compte = other.Compte;
        this.agenda = other.agenda;
        this.Patients = other.Patients;
        this.Patients = other.Patients;
    }

    public void ProgrammerConsultation(LocalDateTime date, Patient patient) throws AjoutRdvException {
        if (patient == null) {
            throw new AjoutRdvException();
        }

        Consultation C;
        if (patient instanceof Adulte) {
            C = new Consultation("1h30", patient.getNom(), patient.getPrenom(), patient.calculerAge(),date);
        } else if (patient instanceof Enfant) {
            C = new Consultation("2h30", patient.getNom(), patient.getPrenom(), patient.calculerAge(),date);
        } else {
            return; // Or throw an exception
        }

        try {
            this.agenda.ajouterRendezVous(date, C);
            DossierPatient d = Patients.get(patient);
            if (d != null) {
                d.listeRDV.add(C);
                Patients.put(patient, d);
                patient.saveToFile();
            }
        } catch (AjoutRdvException e) {
            System.out.println("piroblim");
            // Handle exception
        }
        catch(Exception e) {
            System.out.println("piroblim");
        }
    }

    public void ProgrammerSeanceDeSuivi(LocalDateTime date,int ID, typeSeanceSuivi type) throws AjoutRdvException {
        SeanceSuivi s = new SeanceSuivi("1h",ID,type,date) ;
        try {
            // il faut ajouter un entier pour la duree de la consultation
            this.agenda.ajouterRendezVous(date, s);
        }
        catch (AjoutRdvException e) {
        }
    }
    //
    public void  ProgrammerAtelierDeGroupe(LocalDateTime date , String thematique, HashSet<Patient> patientIds) throws AjoutRdvException {
        // la liste des patients en utilisant le numero de dossier de chacun
        AtelierGroupe s = new AtelierGroupe("1h",thematique,patientIds,date) ;
        try {
            this.agenda.ajouterRendezVous(date, s);
        }
        catch (AjoutRdvException e) {
        }
    }
    //creer BO
    public void CreerBilanOrthophonique() {


        // remplir le projet therapeutique
    }
//    public void redigerConclusion(test test) {
//
//    }

    // sauvegarder ficheDeSuivi
    public void sauvegarderFicheDeSuivi(FicheDeSuivi fiche) {


    }
    //
    public static Orthophoniste loadOrthophonisteFromFile(String name) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name+".bin"))) {
            return (Orthophoniste) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Orthophoniste();
        }
    }
    public static void SaveOrthophonisteFromFile(String name,Orthophoniste o) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name + ".bin"))) {
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }}


    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    //


}


