package ESI.TP.Clinic.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {
    public void handleCliked(MouseEvent actionEvent) {
        System.out.println("Mouse clicked");

        // Get the current stage
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ESI/TP/Clinic/SignUP.fxml"));
        try {
            currentStage.setScene(new javafx.scene.Scene(loader.load()));
            currentStage.setTitle("Add Orthophonist");
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
