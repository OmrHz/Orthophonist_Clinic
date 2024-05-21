package ESI.TP.Clinic.Modules.BO;

public enum Trouble {
    DEGLUTION("Treoubles de deglution"),
    NEURODEVELOPPEMENTAUX("Troubles neuro-developpementaux"),
    COGNITIF("Troubles cognitifs");
    private String nomTrouble;
    // le constructeur
    private Trouble(String nomTrouble) {
        this.nomTrouble=nomTrouble;
    }
    // getter
    public String getNomTrouble() {
        return this.nomTrouble;
    }

}
