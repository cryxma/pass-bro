package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import POJO.User;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Database.DbConnection;

public class RegistryController {

	ObservableList<String> securityQuestionList = FXCollections.observableArrayList("Wie lautet der Mädchenname deiner Mutter?", 
			"Was ist der Name deines ersten Haustieres?", "Was war das erste Konzert, auf dem du warst?",
			"Welche Grunschule hast du besucht?", "Wo bist du geboren?");
	private Stage stage;
	
    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField pfPasswort;

    @FXML
    private Button btRegistrieren;

    @FXML
    private Button btBackToLogin;

    @FXML
    private TextField tfSecurityAnswer;
    
    @FXML
    private Label lblSuccess;
    
    @FXML
    private ComboBox<String> cbSecurityQuestion;

    @FXML
    void handleButtonBackToLoginAction(ActionEvent event) {
    	try {
        	// schliesst Stage Login
        	closeLogin();
        	
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Password Manager - Login");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    // schliesst Stage Login zugriff durch Button btLogin
 	private void closeLogin() {
 		stage = (Stage) btRegistrieren.getScene().getWindow();
 		stage.close();
 	}

    @FXML
    void handleButtonRegistrierenAction(ActionEvent event) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
    	// Datenbank auslesen
    	// HWID überprüfen
    	String hwid = GetHWID();
    	
    	try {
    		User user = new User();
    		user.setUsername(tfUsername.getText());
    		user.setPasswort(pfPasswort.getText());
    		user.setCreationDate(getCurrentDate()); // Gibt nur Datum aus, fehlt Zeit
    		user.setHWID(hwid);
    		user.setSecurityQuestion(cbSecurityQuestion.getValue()); // Weiteres Fenster mit Abfrage zur Sicherheitsfrage- /antwort für die Passwortzurücksetzung
    		user.setsecurityAnswer(tfSecurityAnswer.getText());
    		
    		// Überprüfen, ob Benutzername/ HWID schon vergeben
    		boolean existUsername = DbConnection.CheckExistUsername(tfUsername.getText());
    		boolean existHwid = DbConnection.CheckExistHWID(GetHWID());
    		
    		if (existUsername == true ) {
    			lblSuccess.setText("Benutzername schon vergeben.");
    		} else if (existHwid == true) {
    			lblSuccess.setText("Du hast bereits einen Account.");
    		}
    		else {
    			DbConnection.SetDataInDb(user);
    			lblSuccess.setText("Account erfolgreich erstellt.");
    		}
    	} 
    	catch (Exception ex) {
    		lblSuccess.setText("Fehler: Account nicht erstellt.");
    	}
    }
    
    public Timestamp getCurrentDate()
    {
    	Timestamp ts;
    	return ts = new Timestamp(System.currentTimeMillis());
    }
    
    public String GetHWID() throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	String s = "";
        final String main = System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("COMPUTERNAME") + System.getProperty("user.name").trim();
        final byte[] bytes = main.getBytes("UTF-8");
        final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        final byte[] md5 = messageDigest.digest(bytes);
        int i = 0;
        for (final byte b : md5) {
            s += Integer.toHexString((b & 0xFF) | 0x300).substring(0, 3);
            if (i != md5.length - 1) {
                s += "-";
            }
            i++;
        }
        return s;
    }
    
    @FXML
    private void initialize() throws IOException  {
    	// ComboBox Items geben
    	cbSecurityQuestion.setItems(securityQuestionList);
    	
    	// ComboBox Alignment per CSS auf center setzen -> CSS-Klasse namens "center-aligned" erstellt -> application.css
    	cbSecurityQuestion.getStyleClass().add("center-aligned");
    }
}
