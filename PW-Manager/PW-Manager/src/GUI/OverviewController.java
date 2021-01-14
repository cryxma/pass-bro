package GUI;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Database.DbConnection;
import POJO.PasswordFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OverviewController  implements Initializable{
	
	@FXML
	private TableView<PasswordFile> tableView;
	

    @FXML
    private TableColumn<PasswordFile, String> passwortColumn;
    @FXML
    private TableColumn<PasswordFile, String> beschriftungColumn;
    @FXML
    private TableColumn<PasswordFile, String> urlColumn;
    @FXML
    private TableColumn<PasswordFile, Integer> idColumn;
    @FXML
    private TableColumn<PasswordFile, String> datumUploadColumn;
    @FXML
    private TableColumn<PasswordFile, String> datumErstellungColumn;
    @FXML
    private TableColumn<PasswordFile, String> sicherheitsniveauColumn;
  

    @FXML
    private TextField searchTextField; 
	
    @FXML
    private Button searchButton;
    
    @FXML
    private Button selectAllButton;
    
    @FXML
    private Button deleteButton;
 
    @FXML
    private Button aktualisierenButton;
    
	@FXML
	private AnchorPane mainPaneOverview;

    @FXML
    private Label sicherheitsniveau;
    
    @FXML
    private Button neuErstellen; 

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
    
    
    
    private void writeFile() {
    	
    }
	
	
	private void addFile() {
		
	}
	
	
	

    
    
    @FXML
    void searchFile(ActionEvent event) {
    	
    }
    
	private ObservableList<PasswordFile> dataList = FXCollections.observableArrayList();
	
	
	public void Delete() throws SQLException{
		
	    }
	
	
	// deklaration of selectionmodel permits the selection of multiple rows 
	void selectRows() {
		
		TableViewSelectionModel selectionModel = tableView.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
	}

	
	// button alle auswählen selects all  not empty rows
	@FXML
    void selectAllRows(ActionEvent event) {
		tableView.getSelectionModel().selectAll();
    }
	
	
	
	// updates the tableview from DB
	void updateTable() {
		
	
		try {
			dataList = DbConnection.GetPasswordFileFromDb();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		beschriftungColumn.setCellValueFactory(new PropertyValueFactory<>("Beschriftung"));       
	    urlColumn.setCellValueFactory(new PropertyValueFactory<>("website"));        
	    passwortColumn.setCellValueFactory(new PropertyValueFactory<>("Passwort")); 
		tableView.setItems(dataList);
		
		}
	
	
	//updates the tableview if clicked on aktualisiere
	@FXML
	void updateTableView(ActionEvent event) {
		updateTable();
    	
	}
	
	//dynamic filtering of tableview data
	void searchFiles() {
		       
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		beschriftungColumn.setCellValueFactory(new PropertyValueFactory<>("Beschriftung"));       
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("Website"));        
        passwortColumn.setCellValueFactory(new PropertyValueFactory<>("Passwort"));
        
        //Database reading
        try {
			dataList = DbConnection.GetPasswordFileFromDb();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    	
        
        
        tableView.setItems(dataList);
        
        
        // wrap the ObservableList in a FilteredList
        FilteredList<PasswordFile> filteredFile = new FilteredList<>(dataList, b -> true);
		
		// sets the filter Predicate when the filter changes
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
       	filteredFile.setPredicate(passwordFile -> {
       		
       		// when filter text is empty, display all persons
			if (newValue == null || newValue.isEmpty()) {
				return true;
			}
			
			// compare data with filter text
			String lowerCaseFilter = newValue.toLowerCase();
			
			//check Beschriftung
			if (passwordFile.getBeschriftung().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
				return true;  //filter matches
				//check website
			} else if (passwordFile.getWebsite().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; 
			}
			//TODO: add the other columns
			//check id
			else if (String.valueOf(passwordFile.getId()).indexOf(lowerCaseFilter)!=-1)
			     return true;
			     else  
			    	 return false; // if doesn´t match
			});
		});
		
        
		// wrap filteredList in a sortedList
		SortedList<PasswordFile> sortedData = new SortedList<>(filteredFile);
		
		//  Bind the SortedList comparator to the TableView comparator
		// 	Otherwise, sorting the TableView would have no effect
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
		// add sorted (and filtered) data to the table
		tableView.setItems(sortedData);
	}
	
	
	
	/**
	 * initialize
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		updateTable();
		selectRows();
		searchFiles();
    	
       
	}
	@FXML
	void deleteSelectedRow(ActionEvent event) {
		
	}
	
}
	

