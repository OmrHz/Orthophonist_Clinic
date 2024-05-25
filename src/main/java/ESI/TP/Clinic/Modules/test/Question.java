package ESI.TP.Clinic.Modules.test;

import java.io.Serializable;

public abstract class Question implements Serializable {
    private final String enonce;
    private boolean status=false;
       private int score=0;
    public Question(String enonce) {

        this.enonce = enonce;
    }

    public String getEnonce() {
        return enonce;
    }

    public  void setStatus(boolean status){
        this.status=status;
    };
    public  boolean getStatus(){
        return status;
    };
    public  void setScore(int score){
        this.score=score;
    };
    public  int getScore(){
        return score;
    };


}
