package ESI.TP.Clinic.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AccueilController {

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
}
