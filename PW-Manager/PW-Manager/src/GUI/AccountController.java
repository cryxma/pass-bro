package GUI;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Database.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AccountController {
	ObservableList<String> securityQuestionList = FXCollections.observableArrayList("Wie lautet der Mädchenname deiner Mutter?", 
			"Was ist der Name deines ersten Haustieres?", "Was war das erste Konzert, auf dem du warst?",
			"Welche Grunschule hast du besucht?", "Wo bist du geboren?");

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblCreationDate;

    @FXML
    private Label lblHwid;

    @FXML
    private Label lblSecurityQuestion;

    @FXML
    private Label lblSecurityAnswer;

    @FXML
    private AnchorPane btChangePassword;

    @FXML
    private PasswordField pfNewPasswort;

    @FXML
    private PasswordField pfNewPasswortConfirmation;

    @FXML
    private ComboBox<String> cbNewSecurityQuestion;

    @FXML
    private TextField tfNewSecurityAnswer;

    @FXML
    private TextField tfNewSecurityAnswerConfirmation;

    @FXML
    private Button btChangeSecurityInformations;

    @FXML
    void handleButtonChangePasswordAction(ActionEvent event) {
    	
    }

    @FXML
    void handleButtonChangeSecurityDataAction(ActionEvent event) {

    }
    
    @FXML
    private void initialize() throws IOException  {
    	// ComboBox Items geben
    	cbNewSecurityQuestion.setItems(securityQuestionList);
    	
    	// ComboBox Alignment per CSS auf center setzen -> CSS-Klasse namens "center-aligned" erstellt -> application.css
    	cbNewSecurityQuestion.getStyleClass().add("center-aligned");
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
}
