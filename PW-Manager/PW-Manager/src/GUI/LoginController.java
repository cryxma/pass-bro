package GUI;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import Database.DbConnection;
import POJO.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController {
	 //private static final EventHandler<? super MouseDragEvent> MouseDragEvent = null;

    Stage stage;
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
	 private Button btResetAccount;
	 
	 @FXML
	 private Button btResetPassword;

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
    
    private void logIn() throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
    	String username = tfUsername.getText().toString();
    	String passwort = pfPasswort.getText().toString();
    	
    	// HWID überprüfen
    	String hwid = GetHWID().toString();
    	
    	boolean checkExistAccount = DbConnection.CheckExistAccount(username, passwort);
    	boolean checkValidHwid = DbConnection.CheckValidHwid(username, hwid);
    	
    	if (checkExistAccount == false) {
    		lblSuccess.setText("Benutzername oder Passwort falsch.");
    	} else if (checkValidHwid == false) {
    		lblSuccess.setText("Du kannst dich nur von deinem Computer einloggen.");
    	} else {
    			
    		
			/*	Login Fenster wird geschlossen
    			
            	Node source = (Node) event.getSource();
            	Stage loginStage = (Stage) source.getSource().getWindow();
            	loginStage.close();
            */
    		
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                
                // Communication between LoginController and OverviewController to get the active user
                MainWindowController owController = fxmlLoader.getController();
                owController.GetActiveUser(tfUsername.getText());
                
                Stage stage = new Stage();
                stage.setTitle("Password Manager - Welcome");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
    	}
    }
    
	 @FXML
	 void handleButtonResetAccountAction(ActionEvent event) {
         try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ResetHwid.fxml"));
             Parent root = (Parent) fxmlLoader.load();
             Stage stage = new Stage();
             stage.setTitle("Password Manager - Account zurücksetzen");
             stage.setScene(new Scene(root));
             stage.show();
         } catch (IOException ioe) {
             ioe.printStackTrace();
         }
	 }
	 
	 @FXML
	 void handleButtonResetDragOverAction(MouseDragEvent event) {
		 // Hover unterstrichen
	 }
	 
	 @FXML
	 void handleButtonResetPasswordAction(ActionEvent event) {
         try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
             Parent root = (Parent) fxmlLoader.load();
             Stage stage = new Stage();
             stage.setTitle("Password Manager - Passwort zurücksetzen");
             stage.setScene(new Scene(root));
             stage.show();
         } catch (IOException ioe) {
             ioe.printStackTrace();
         }
	 }
	 
	 @FXML
	 private void initialize() throws IOException  {
	    	
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

