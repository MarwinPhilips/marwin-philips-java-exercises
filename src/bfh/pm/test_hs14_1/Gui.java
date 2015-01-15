package bfh.pm.test_hs14_1;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Gui implements Observer {

	private Exchange exchange;
	@FXML
	Button btnStart;
	@FXML
	Button btnStop;
	@FXML
	Button btnAdd;
	@FXML
	Button btnSelect;
	@FXML
	Label lblCurrency;
	@FXML
	ComboBox<HashMap<String, Double>> cbCurrencies;
	@FXML
	TextField txtCurrency;
	@FXML
	TextField txtInitialRate;
	
	
	public Gui() {		
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}

	public void init(Exchange ex) {
		this.exchange=ex;
	}
	
	
}
