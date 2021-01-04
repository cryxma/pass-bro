package GUI;

import java.net.URL;
import java.util.ResourceBundle;

import POJO.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class AccountController implements Initializable{

	@FXML private Label displayedUsername;
	
	/*@Override
	public void GetActiveUser(String text) {
		// TODO Auto-generated method stub
		
	}
	*/
	//extends MainWindowController 
	
	
	private User activeUser;
	

	public void initData(User user) {
		
		activeUser = user;
		displayedUsername.setText(activeUser.getUsername());
		
	}
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}





	public void GetActiveUser(String text) {
		// TODO Auto-generated method stub
		
	} 
	
}
