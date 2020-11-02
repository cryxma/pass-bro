package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField pfPasswort;

    @FXML
    private Button btLogin;

    @FXML
    private Button btRegistrieren;

    @FXML
    void handleButtonLoginAction(ActionEvent event) {

    }

    @FXML
    void handleButtonRegistrierenAction(ActionEvent event) {
        // Neues Fenster öffnen
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registry.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Password Manager - Registrierung");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
