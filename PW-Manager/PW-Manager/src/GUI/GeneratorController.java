package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import PWDG.Bezeichner;
import PWDG.Generator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class GeneratorController implements Initializable {
	
	private static final int minPassLaenge = 8;
	private static final int maxPassLaenge = 50;
	private static final Bezeichner standardBezeichner = Bezeichner.NUMMERN;
	
	
	@FXML
	private AnchorPane mainPaneGenerator;

	@FXML
	private Button generiere;
	@FXML
	private CheckBox symbole;
	@FXML
	private CheckBox kleinbuchstaben;
	@FXML
	private CheckBox grossbuchstaben;
	@FXML
	private CheckBox ziffern;
	@FXML
	private TextField passLaenge;
	@FXML
	private TextField ausgabePasswort;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String regEx = "^\\d*$";
		passLaenge.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches(regEx)) {
				passLaenge.setText(oldValue);
			}
		});
	}

	public void generierePasswort() {
		Generator generator = ladeSetup();
		schreibeInTextfeld(generator.pwd());
	}

	private Generator ladeSetup() {
		String passwortLaenge = passLaenge.getText();
		int passLaenge = parseInt(passwortLaenge, minPassLaenge);
		Generator generator = Generator.factory(passLaenge);
		if (symbole.isSelected()) {
			generator.addBezeichner(Bezeichner.SONDERZEICHEN);
		}
		if (kleinbuchstaben.isSelected()) {
			generator.addBezeichner(Bezeichner.KLEINBUCHSTABEN);
		}
		if (grossbuchstaben.isSelected()) {
			generator.addBezeichner(Bezeichner.GROSSBUCHSTABEN);
		}
		if (ziffern.isSelected()) {
			generator.addBezeichner(Bezeichner.NUMMERN);
		}
		
		if (generator.getErwarteteZeichen().size()==0) {
			generator.addBezeichner(standardBezeichner);
		}
		return generator;	
	}
	
	private void schreibeInTextfeld(String passwort) {
		ausgabePasswort.setText(passwort);		
	}

	public int parseInt(String zahl, int standard) {
		int wert = standard; // Standard Passwort Länge
		try {
			wert = Integer.parseInt(zahl);
			if (wert < minPassLaenge) {
				wert = minPassLaenge;
			}
			else if (wert > maxPassLaenge) {
				wert = maxPassLaenge;
			}
		} catch (NumberFormatException e) {
			// Es wird einfach der standard zurück gegeben
		}
		return wert;
	}

	// test button neu erstellen
	@FXML
	void openDataEinfuegenView(ActionEvent event) {
		Parent pane = null;
		try {
			pane = FXMLLoader.load(getClass().getResource("DatensatzEinfuegenView.fxml"));
			mainPaneGenerator.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
