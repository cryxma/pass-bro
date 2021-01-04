package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainWindowController {	
	
	Stage stage;
	String activeUser;
	
    @FXML
    private AnchorPane mainPane;
    
    @FXML
    private BorderPane borderPane;

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button acoountButton;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button overviewButton;

    @FXML
    private Button hilfeButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button generatorButton;

	private Label tfUsername;
    

	@FXML
	private void handleButtonOpenOverviewAction(ActionEvent event) {
		setViewName("Overview");
	}
		
	
	@FXML
	private void handleButtonOpenGeneratorViewAction(ActionEvent event) {
		setViewName("Generator");
	}
	
	
	@FXML
	void handleButtonOpenHelpViewAction(ActionEvent event) {
		setViewName("Help");
	}
	
	@FXML
<<<<<<< HEAD
	void handleButtonOpenAccountViewAction(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Account.fxml"));
		Parent root = null;
		try {
			root = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		borderPane.setCenter(root);
		
		AccountController accountcontroller = fxmlLoader.getController();
		//accountcontroller.getLabel().setText(tfUsername.getText());

=======
	void handleButtonOpenAccountViewAction(ActionEvent event) throws IOException {
		setViewName("Account");
>>>>>>> refs/remotes/origin/master
	}
	
	private void setViewName(String nameFxmlView) {
		Parent pane = null;
		try {
			pane = FXMLLoader.load(getClass().getResource(nameFxmlView + ".fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		borderPane.setCenter(pane);
	}

	@FXML
	void handleButtonLogOutAction(ActionEvent event) {
		stage = (Stage) logoutButton.getScene().getWindow();
    	stage.close();
    	
    	 try {
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
	
	@FXML
    void initialize() {

    }
	
	
<<<<<<< HEAD
	public void GetActiveUser(String text) {
		// TODO Auto-generated method stub
		
		
=======
	public void GetActiveUser(String username) {
		this.activeUser = username;
>>>>>>> refs/remotes/origin/master
	}
	
	public String passUsername() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        try {
			Parent root = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		MainWindowController mainWindowController = fxmlLoader.getController();
        return mainWindowController.GetActiveUser(tfUsername.getText());	}
	
}
