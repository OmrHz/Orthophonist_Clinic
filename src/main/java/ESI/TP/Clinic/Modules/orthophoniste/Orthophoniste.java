package ESI.TP.Clinic.Modules.orthophoniste;
import ESI.TP.Clinic.Modules.Fiche.FicheDeSuivi;
import ESI.TP.Clinic.Modules.RDV.*;
import ESI.TP.Clinic.Modules.patient.DossierPatient;
import ESI.TP.Clinic.Modules.patient.Patient;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
            if (!agenda.containsKey(date)) {
                LocalDateTime previousRDV = agenda.lowerKey(date);
                LocalDateTime nextRDV = agenda.higherKey(date);

                if (previousRDV != null && nextRDV != null) {
                    if (date.isBefore(previousRDV.plusHours(3)) || date.isAfter(nextRDV.minusHours(3))) {
                        throw new AjoutRdvException("Creux insuffisant");
                    }
                }
                agenda.put(date, rdv);
            } else {
                throw new AjoutRdvException("Un rendez-vous est déjà programmé à cette date/heure");
            }
        }

        public void afficherAgenda() {
            for (Map.Entry<LocalDateTime, RendezVous> entry : agenda.entrySet()) {
                LocalDateTime key = entry.getKey();
                RendezVous value = entry.getValue();
                System.out.println("Date: " + key + ", " + value);
            }
        }
    }

    private CompteOrthophoniste Compte;
    private Agenda agenda;
    private HashSet<DossierPatient> listeDossierPatients = new HashSet<>();
    private HashSet<Patient> listePatients = new HashSet<>();

    public CompteOrthophoniste getCompte() {
        return Compte;
    }

    public void setCompte(CompteOrthophoniste compte) {
        this.Compte = compte;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public HashSet<DossierPatient> getListeDossierPatients() {
        return listeDossierPatients;
    }

    public void setListeDossierPatients(HashSet<DossierPatient> listeDossierPatients) {
        this.listeDossierPatients = listeDossierPatients;
    }

    public HashSet<Patient> getListePatients() {
        return listePatients;
    }

    public void setListePatients(HashSet<Patient> listePatients) {
        this.listePatients = listePatients;
    }

    public Orthophoniste(CompteOrthophoniste Compte, Agenda agenda) {
        this.Compte = Compte;
        this.agenda = agenda;
    }

    public Orthophoniste(String Nom, String Prenom, String Adresse, String NumeroTelephone, String Email, String MotDePasse) {
        this.Compte = new CompteOrthophoniste(Nom, Prenom, Adresse, NumeroTelephone, Email, MotDePasse);
    }

    public Orthophoniste() {
    }

    public void priseEnCharge(Patient patient) {
        if (!patient.getPrisEnCharge()) {
            this.listePatients.add(patient);
            patient.setPrisEnCharge();
            patient.saveToFile();
            DossierPatient d = new DossierPatient(patient);
            Orthophoniste loadedOrthophoniste = loadOrthophonisteFromFile(this.Compte.getNom());
            this.copyFrom(loadedOrthophoniste);
            this.listeDossierPatients.add(d);
            SaveOrthophonisteFromFile(this.Compte.getNom(), loadedOrthophoniste);
        }
    }

    private void copyFrom(Orthophoniste other) {
        this.Compte = other.Compte;
        this.agenda = other.agenda;
        this.listeDossierPatients = other.listeDossierPatients;
        this.listePatients = other.listePatients;
    }

    public static Orthophoniste loadOrthophonisteFromFile(String name) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name + ".bin"))) {
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
}

