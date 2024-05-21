package ESI.TP.Clinic.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUp {
    public TextField email;
    public TextField nom;
    public TextField prenom;
    public TextField Address;
    public TextField Num;
    public PasswordField password;

    @FXML
   private void HandleSignUp(){
        String Email = email.getText();

    }
}
