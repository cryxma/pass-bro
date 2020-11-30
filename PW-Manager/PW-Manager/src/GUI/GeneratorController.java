package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class GeneratorController {
	
	@FXML
    private AnchorPane mainPaneGenerator;	
	
	// test
    @FXML
    void openDataEinfuegenView(ActionEvent event) {
    	Parent pane = null;
		try {
			pane = FXMLLoader.load(getClass().getResource("DatensatzEinfuegenView.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainPaneGenerator.getChildren().setAll(pane);
		}
    
}
