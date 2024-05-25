package ESI.TP.Clinic.Modules.orthophoniste;
import ESI.TP.Clinic.Modules.Fiche.FicheDeSuivi;
import ESI.TP.Clinic.Modules.RDV.*;
import ESI.TP.Clinic.Modules.patient.DossierPatient;
import ESI.TP.Clinic.Modules.patient.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.*;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class Orthophoniste implements Serializable {
    private static final long serialVersionUID = 1L;
    public class Agenda implements Serializable {
        private static final long serialVersionUID = 1L;
        private NavigableMap<LocalDateTime, RendezVous> agenda=new TreeMap<LocalDateTime, RendezVous>();
        public Agenda() {

        }
        public NavigableMap<LocalDateTime, RendezVous> getAgenda() {
            return this.agenda;
        }
        //
        public static ArrayList<Integer> parseTimeString(String timeString) {
            // Split the string using 'h' as a delimiter
            String[] parts = timeString.split("h");

            // Convert the resulting string array to an ArrayList<Integer>
            ArrayList<Integer> timeParts = new ArrayList<>();
            for (String part : parts) {
                // Trim and parse each part to an integer
                timeParts.add(Integer.parseInt(part));
            }
            if(timeParts.size()==1) {
                timeParts.add(0);
            }
            return timeParts;
        }

        //
        public void ajouterRendezVous(LocalDateTime appointmentDateTime, RendezVous rdv, String patientType) throws AjoutRdvException {
            ArrayList<Integer> rdvTime = parseTimeString(rdv.getDuree());
            int gapHours = rdvTime.get(0);
            int gapMinutes = rdvTime.get(1);
            Duration newRdvDuration = Duration.ofHours(gapHours).plusMinutes(gapMinutes);
            if (agenda.isEmpty()) {
                agenda.put(appointmentDateTime, rdv);
                return;
            } else {
                LocalDateTime previousRDVTime = agenda.lowerKey(appointmentDateTime);
                LocalDateTime nextRDVTime = agenda.higherKey(appointmentDateTime);

                // Determine the gap for the previous appointment
                if (previousRDVTime != null) {
                    RendezVous previousRdv = agenda.get(previousRDVTime);
                    ArrayList<Integer> previousRdvTime = parseTimeString(previousRdv.getDuree());
                    Duration previousRdvDuration = Duration.ofHours(previousRdvTime.get(0)).plusMinutes(previousRdvTime.get(1));
                    if (appointmentDateTime.isBefore(previousRDVTime.plus(previousRdvDuration))) {
                        throw new AjoutRdvException("Creux insuffisant");
                    }
                }

                // Determine the gap for the next appointment
                if (nextRDVTime != null) {
                    if (appointmentDateTime.plus(newRdvDuration).isAfter(nextRDVTime)) {
                        throw new AjoutRdvException("Creux insuffisant");
                    }
                }

                // If the time gaps are sufficient, add the appointment
                agenda.put(appointmentDateTime, rdv);
            }
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
            patient.saveToFile();
            this.addPatients(patient, d);

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
            return;
        }

        DossierPatient d = Patients.get(patient);
        if (d.getListeRendezVous().isEmpty()) { // nouveau patient
            Patients.put(patient, d); // pour ajouter le patient a la liste des patients avec un dossier vide
            this.agenda.ajouterRendezVous(date, C, patient.getClass().toString());
            d.addRdv(C);
            Patients.put(patient, d);// pour mettre a jour le dossier si une consultation a ete programmée
            patient.saveToFile();
        }
        else throw new AjoutRdvException("ce patient existe deja ");


    }
    //
    public void ProgrammerSeanceDeSuivi(LocalDateTime date,int numeroDossier, typeSeanceSuivi type) throws AjoutRdvException {
        SeanceSuivi s = new SeanceSuivi("1h0",numeroDossier,type,date) ;
        System.out.println("ranyhna1");
        ArrayList<Patient> list = this.chercherPatientsParNumeroDossiers(new ArrayList<>(Arrays.asList(numeroDossier)));
        System.out.println("ranyhna2");

        if (list.isEmpty())
            throw new AjoutRdvException("aucun patient trouvé avec ce numero !");
        if (list.size()>1)
            throw new AjoutRdvException("deux patients ont le meme numero deossier ! ");
        DossierPatient d = Patients.get(list.get(0));
        if(d.getListeRendezVous().isEmpty())
            throw new AjoutRdvException("il faut programmer une consultation d'abord ! ");
        this.agenda.ajouterRendezVous(date, s,"");// une heure
        d.addRdv(s);
        Patients.put(list.get(0), d);
        list.get(0).saveToFile();
        System.out.println("ranyhna4");


    }
    //
    public ArrayList<Patient> chercherPatientsParNumeroDossiers(ArrayList<Integer> numeroDossiers) {
        ArrayList<Patient> patients = new ArrayList<>();
        for (Integer numeroDossier : numeroDossiers) {
            for (Map.Entry<Patient, DossierPatient> entry : this.getPatients().entrySet()) {
                DossierPatient dossierPatient = entry.getValue();
                if (dossierPatient.getNumeroDossier() == numeroDossier) {
                    patients.add(entry.getKey());
                    break; // Move to the next numeroDossier
                }
            }
        }
        return patients;
    }
    //
    public void  ProgrammerAtelierDeGroupe(LocalDateTime date , String thematique, ArrayList<Integer> numeroDossiers) throws AjoutRdvException {
        // la liste des patients en utilisant le numero de dossier de chacun
        AtelierGroupe s = new AtelierGroupe("1h00",thematique,numeroDossiers,date) ;
        this.agenda.ajouterRendezVous(date, s,""); // une heure
        ArrayList<Patient> list = this.chercherPatientsParNumeroDossiers(numeroDossiers);
        for ( Map.Entry<Patient,DossierPatient> entry : this.getPatients().entrySet() ) {
            if( list.contains(entry.getKey()) ) {
                entry.getValue().addRdv(s);
                Patients.put(entry.getKey(),entry.getValue());
                entry.getKey().saveToFile();
            }
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



