package GUI;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class OverviewController {
	
    
	
	

    @FXML
    private TableColumn<?, ?> passwortColumn;

  

    @FXML
    private TableView<?> tableView;

    

   
    @FXML
    private TableColumn<?, ?> beschriftungColumn;

    @FXML
    private TableColumn<?, ?> datumUploadColumn;

    @FXML
    private TableColumn<?, ?> urlColumn;

    

    @FXML
    private TableColumn<?, ?> datumErstellungColumn;

    @FXML
    private TableColumn<?, ?> sicherheitsniveauColumn;

  

  
	
	
    @FXML
    private Button selectAllButton;


    @FXML
    private Button deleteButton;

    
    @FXML
    private TextField searchTextField;
    
    
    
    

	@FXML
    private Button neuErstellen; 
	
	@FXML
	private AnchorPane mainPaneOverview;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label sicherheitsniveau;

    @FXML
    private Button searchButton;

    @FXML
    private Label passwort;

    @FXML
    private Label beschriftung;

    @FXML
    private VBox vBoxForVBoxes;

    @FXML
    private VBox vBoxForEveryFile;

    @FXML
    private TextField beschriftungsSuche;

    @FXML
    private Label linkWebseite;

    @FXML
    private Separator separator;
    
    
    
    
    
    @FXML
    void selectAllRows(ActionEvent event) {

    }

    @FXML
    void deleteSelectedRow(ActionEvent event) {

    }
    

    @FXML
    void initialize() {
        assert sicherheitsniveau != null : "fx:id=\"sicherheitsniveau\" was not injected: check your FXML file 'Overview.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'Overview.fxml'.";
        assert passwort != null : "fx:id=\"passwort\" was not injected: check your FXML file 'Overview.fxml'.";
        assert beschriftung != null : "fx:id=\"beschriftung\" was not injected: check your FXML file 'Overview.fxml'.";
        assert vBoxForVBoxes != null : "fx:id=\"vBoxForVBoxes\" was not injected: check your FXML file 'Overview.fxml'.";
        assert vBoxForEveryFile != null : "fx:id=\"vBoxForEveryFile\" was not injected: check your FXML file 'Overview.fxml'.";
        assert beschriftungsSuche != null : "fx:id=\"beschriftungsSuche\" was not injected: check your FXML file 'Overview.fxml'.";
        assert linkWebseite != null : "fx:id=\"linkWebseite\" was not injected: check your FXML file 'Overview.fxml'.";
        assert separator != null : "fx:id=\"separator\" was not injected: check your FXML file 'Overview.fxml'.";

    }
	
    @FXML
    void searchFile(ActionEvent event) {
    	
    }
    
    
    private void writeFile() {
    	
    }
	
	
	private void addFile() {
		
	}
}
