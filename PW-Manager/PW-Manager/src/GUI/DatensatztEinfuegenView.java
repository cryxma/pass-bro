package GUI;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;

import Database.DbConnection;
import POJO.PasswordFile;
import POJO.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DatensatztEinfuegenView {
	
    @FXML
    private GridPane datensatztEinfuegenView;

    @FXML
    private TextField eingabePasswort ;

    @FXML
    private TextField eingabeWebseite;

    @FXML
    private TextField eingabeBeschriftung;
    
    @FXML
    private Button neuesFileErstellen;
    
    @FXML
    private Label labelINFO;
    
    @FXML
    void neuesFileErstellen(ActionEvent event) throws SQLException {
		PasswordFile passFile = new PasswordFile(0, null, null, null);

    	try {
        	passFile.setBeschriftung(eingabeBeschriftung.getText());
        	passFile.setWebsite(eingabeWebseite.getText());
        	passFile.setPassword(eingabePasswort.getText());
    		
        	DbConnection.SetPasswordFileInDb(passFile);
			labelINFO.setText("Account erfolgreich erstellt.");
    	}catch (Exception ex) {
    		ex.printStackTrace();
    		labelINFO.setText("hoo");
    	}
    }	
    
    
   
    /*
    private void sendPassFileToDb() throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
    	String beschriftung = eingabeBeschriftung.getText().toString();
    	String website = eingabeWebseite.getText().toString();
    	String passwort  = eingabePasswort.getText().toString();

   	
    	
	
    boolean checkExistAccount = DbConnection.CheckExistAccount(username., passwort);
	
	if (checkExistAccount == false) {
		lblSuccess.setText("Benutzername oder Passwort falsch.");
	} else if (checkValidHwid == false) {
		lblSuccess.setText("Du kannst dich nur von deinem Computer einloggen.");
	} else {
    
    
    */
    
    
    
    
    
   /* @FXML
    void handleButtonRegistrierenAction(ActionEvent event) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
    	// Datenbank auslesen
    	// HWID überprüfen
    	//String hwid = GetHWID();
    	
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
    */
    public Timestamp getCurrentDate()
    {
    	Timestamp ts;
    	return ts = new Timestamp(System.currentTimeMillis());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    // erstellt neues ordner wenn nicht vorhanden und ein text datei mit den Eingaben im DatensatztEinfügenView
    @FXML
    void neuesFileErstellen(ActionEvent event) {
    	
    	String dir = "C:\\Users\\marus\\PassBroTests";

        Path pathForDir = Paths.get(dir);

        if (!Files.exists(pathForDir)) {
            
			try {
				Files.createDirectory(pathForDir);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Directory created");
			
        } else {
            System.out.println("Directory already exists");
        }
        
        
    	String fileToCreate = "C:\\Users\\marus\\PassBroTests\\"+eingabeBeschriftung.getText()+".txt";
    	Path pathForFile = Paths.get(fileToCreate);	
    	
    	if(!Files.exists(pathForFile)) {
    		System.out.println("Textfile created");
   
			try {
				String test = eingabeBeschriftung.getText()+" "+ eingabeWebseite.getText()+" "+ eingabePasswort.getText();
				
				//hier verschlüsselung methode für passwort oder alle die eingaben einfüegen
				byte[] bs = test.getBytes();
				Path writtenFilePath = Files.write(pathForFile, bs);
				System.out.println("Geschriebener Inhalt in file "+ fileToCreate+":\n"+ new String(Files.readAllBytes(writtenFilePath)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
    	}else {
    		System.out.println("Textfile already exist");
    		// ein pop up fenster hinzufüegen um zu fragen ob ma den file überschreiben will
    		//anzeigen in dem ordner mit focus = true dass den file schon existiert
    	}
    }
    */
    
    
}
