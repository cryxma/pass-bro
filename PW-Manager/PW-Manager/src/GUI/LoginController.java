package GUI;

import java.io.IOException;
import java.sql.SQLException;

import Database.DbConnection;
import POJO.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	 private Label lblSuccess;

    @FXML
    void handleButtonLoginAction(ActionEvent event) {
    	try {
    		logIn();
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
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
    
    private void logIn() throws SQLException {
    	String username = tfUsername.getText().toString();
    	String passwort = pfPasswort.getText().toString();
    	
    	boolean result = DbConnection.loginQuery(username, passwort);
    	
    	if (result == false) {
    		lblSuccess.setText("Benutzername oder Passwort falsch.");
    	} else {
    		// Neues Fenster öffnen
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Overview.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Password Manager - Übersicht");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
    	}
    }
}

