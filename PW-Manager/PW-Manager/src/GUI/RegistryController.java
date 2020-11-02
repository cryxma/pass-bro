package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
import java.time.LocalDateTime;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Database.DbConnection;

public class RegistryController {

	ObservableList<String> securityQuestionList = FXCollections.observableArrayList("Wie lautet der Mädchenname deiner Mutter?", 
			"Was ist der Name deines ersten Haustieres?", "Was war das erste Konzert, auf dem du warst?",
			"Welche Grunschule hast du besucht?", "Wo bist du geboren?");
	
    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField pfPasswort;

    @FXML
    private Button btRegistrieren;

    @FXML
    private Button btExit;

    @FXML
    private TextField tfSecurityAnswer;
    
    @FXML
    private Label lblSuccess;
    
    @FXML
    private ComboBox<String> cbSecurityQuestion;

    @FXML
    void handleButtonExitAction(ActionEvent event) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	// Eigenes Fenster initialisieren
    	//Stage stage = (Stage) btExit.getScene().getWindow();
    	
    	// Fenster schließen
    	//stage.close();
    	tfUsername.setText(getHWID()); // 326-3e6-37a-340-30a-31b-356-31f-33f-321-3fc-35e-3af-31c-3aa-3fc
    }

    @FXML
    void handleButtonRegistrierenAction(ActionEvent event) throws SQLException {
    	// Datenbank auslesen
    	// HWID überprüfen
    	
    	try {
    		User user = new User();
    		user.setUsername(tfUsername.getText());
    		user.setPasswort(pfPasswort.getText());
    		user.setCreationDate(getCurrentDate()); // Gibt nur Datum aus, fehlt Zeit
    		user.setHWID(12345);
    		user.setSecurityQuestion(cbSecurityQuestion.getValue());
    		user.setsecurityAnswer(tfSecurityAnswer.getText());
    		DbConnection.SetDataInDb(user);
    		
        	lblSuccess.setText("Info: Account erfolgreich erstellt.");
    	} 
    	catch (Exception ex) {
    		lblSuccess.setText("Fehler: Account nicht erstellt.");
    	}
    }
    
    public LocalDateTime getCurrentDate()
    {
    	LocalDateTime date = LocalDateTime.now();
    	return date.withNano(0);
    }
    
    public String getHWID() throws NoSuchAlgorithmException, UnsupportedEncodingException {
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
