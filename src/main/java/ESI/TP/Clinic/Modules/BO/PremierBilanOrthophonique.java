package ESI.TP.Clinic.Modules.BO;

import ESI.TP.Clinic.Modules.test.categorieQuestion;

import java.util.HashMap;
import java.util.TreeMap;

public class PremierBilanOrthophonique extends BilanOrthophonique{
    // la premiere etape : l'anamnese
    HashMap<categorieQuestion,TreeMap<String,String>> Questionnaire = new HashMap<categorieQuestion,TreeMap<String,String>>();
    // un dictionnaire de categories ou chaque categorie comporte plusieurs questions avec une reponse libre
}
