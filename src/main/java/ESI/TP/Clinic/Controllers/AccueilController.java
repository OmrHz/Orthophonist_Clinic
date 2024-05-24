package ESI.TP.Clinic.Controllers;

import ESI.TP.Clinic.Modules.orthophoniste.Orthophoniste;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AccueilController {

    private Orthophoniste ort;
    @FXML
    private Text fullname;



    public void HandleSeDeconnecter(MouseEvent event) {
        System.out.println("Mouse clicked");

        Stage currStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/Views/Login.fxml"));
        try {
            currStage.setScene(new javafx.scene.Scene(loader.load()));
            currStage.setTitle("Add Orthophonist");
            currStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOrthophoniste(Orthophoniste ort) {
        this.ort = ort;
        this.fullname.setText("DR " + ort.getCompte().getNom() + " " + ort.getCompte().getPrenom());
    }

}
