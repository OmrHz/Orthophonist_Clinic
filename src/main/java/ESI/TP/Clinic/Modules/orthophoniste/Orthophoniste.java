package ESI.TP.Clinic.Modules.orthophoniste;
import ESI.TP.Clinic.Modules.Fiche.FicheDeSuivi;
import ESI.TP.Clinic.Modules.RDV.*;
import ESI.TP.Clinic.Modules.patient.Adulte;
import ESI.TP.Clinic.Modules.patient.DossierPatient;
import ESI.TP.Clinic.Modules.patient.Enfant;
import ESI.TP.Clinic.Modules.patient.Patient;

import java.util.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Orthophoniste implements Serializable {
    private static final long serialVersionUID = 1L;
    public class Agenda implements Serializable{
        private static final long serialVersionUID = 1L;
        private TreeMap<LocalDateTime, RendezVous> agenda;
        public Agenda() {

            this.agenda = new TreeMap<LocalDateTime, RendezVous>();
            agenda.put(null, null);
        }
        public void ajouterRendezVous(LocalDateTime date, RendezVous rdv) throws AjoutRdvException {
            Scanner scanner = new Scanner(System.in);
            String dateTimeString = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime appointmentDateTime = LocalDateTime.parse(dateTimeString, formatter);
            scanner.close();
            if (!agenda.containsKey(appointmentDateTime)) {
                LocalDateTime previousRDV =   agenda.lowerKey(appointmentDateTime);
                //  if ( previousRDV !=null ) {
                if (previousRDV.getYear()==appointmentDateTime.getYear() && previousRDV.getMonthValue()==appointmentDateTime.getMonthValue() && previousRDV.getDayOfMonth()==appointmentDateTime.getDayOfMonth()) {
                    LocalDateTime nextRDV =   agenda.higherKey(appointmentDateTime);

                    if (appointmentDateTime.getHour()-previousRDV.getHour()<3  &&  nextRDV.getHour()  - appointmentDateTime.getHour() < 3 )
                        throw new AjoutRdvException("Creux insuffisant ") ;
                }
                agenda.put(appointmentDateTime, rdv);
            }
            else throw new AjoutRdvException("Un rendez-vous est deja programmé dans cette date/heure ") ;
            // il faut verifier
            // }
        }

        public void afficherAgenda() {
            for (Map.Entry<LocalDateTime, RendezVous> entry : agenda.entrySet()) {
                LocalDateTime key = entry.getKey();
                RendezVous value = entry.getValue();
                if ( value instanceof AtelierGroupe) {
                    System.out.println("Date: " + key + ", Thematique: " + ((AtelierGroupe)value).getThematique() + ", Duree: "+ ((AtelierGroupe) value).getDuree());
                }
                else if (value instanceof Consultation) {
                    System.out.println("Date: " + key + ", Nom: " + ((Consultation)value).getNom() + ", Duree: "+ ((Consultation) value).getDuree());
                }
                else if (value instanceof SeanceSuivi) {
                    System.out.println("Date: " + key + ", Numero dossier: " + ((SeanceSuivi)value).getNumeroDossier() + ", Duree: "+ ((SeanceSuivi) value).getDuree());
                }
            }

        }
    }
    public CompteOrthophoniste Compte;
    Agenda agenda ;
    HashSet<DossierPatient> listeDossierPatients = new HashSet<DossierPatient>();
    HashSet<Patient> listePatients = new HashSet<Patient>();

    public Orthophoniste(CompteOrthophoniste Compte , Agenda agenda ) {
        this.Compte=Compte;
        this.agenda=agenda;
        // TODO Auto-generated constructor stub
    }
    // creer un compte
    public Orthophoniste() {
        Scanner sc = new Scanner(System.in);
//		appel au constructuer
        //String Nom, String Prenom ,String Adresse , String NumeroTelephone ,String AdresseEMail , String MotDePasse
        System.out.println("Nom");
        String Nom = sc.nextLine();
        System.out.println("Prenom");
        String Prenom = sc.nextLine();
        System.out.println("Adesse");
        String Adresse = sc.nextLine();
        System.out.println("Numero de Telephone");
        String NumeroDeTelephone = sc.nextLine();
        System.out.println("Email");
        String Email = sc.nextLine();
        System.out.println("Mot de passe");
        String MotDePasse = sc.nextLine();
        this.Compte=new CompteOrthophoniste(Nom,Prenom,Adresse,NumeroDeTelephone,Email,MotDePasse);
        sc.close();

    }

    //les methodes
    //
    public void priseEnCharge(Patient patient) {
        if (!patient.getPrisEnCharge()) {
            this.listePatients.add(patient);
            patient.setPrisEnCharge();
            DossierPatient d = new DossierPatient(patient);
            this.listeDossierPatients.add(d);
        }

    }
    //
    public void ProgrammerConsultation(LocalDateTime date , Patient patient) throws AjoutRdvException {
        if ( patient instanceof Adulte) {
            Consultation C = new Consultation("1h30",patient.toString(),patient.getNom(),patient.getPrenom(),patient.calculerAge());
            try {
                // il faut ajouter un entier pour la duree de la consultation
                this.agenda.ajouterRendezVous(date, C);
            }
            catch (AjoutRdvException e) {
            }
        }
        else if (patient instanceof Enfant){
            Consultation C = new Consultation("2h30",patient.toString(),patient.getNom(),patient.getPrenom(),patient.calculerAge());
            try {
                this.agenda.ajouterRendezVous(date, C);
            }
            catch (AjoutRdvException e) {
            }
        }
    }
    public void ProgrammerSeanceDeSuivi(LocalDateTime date,int ID, typeSeanceSuivi type) throws AjoutRdvException {
        SeanceSuivi s = new SeanceSuivi("1h",ID,type) ;
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
        AtelierGroupe s = new AtelierGroupe("1h",thematique,patientIds) ;
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



}
