package GUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DatensatztEinfuegenView {

    @FXML
    private GridPane datensatztEinfuegenView;

    @FXML
    private TextField eingabeWebseite;

    @FXML
    private TextField eingabePasswort;

    @FXML
    private TextField eingabeBeschriftung;
    
    @FXML
    private Button neuesFileErstellen;
    
    
    //test
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
    		// ein pop up fenster hinzufüegen um zu frage ob ma den file überschreiben will
    		//anzeigen in dem ordner mit focus = true dass den file schon existiert

    	}
    }
    
    
}
