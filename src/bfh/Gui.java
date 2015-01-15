package bfh;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
	ComboBox<String> cbCurrencies;
	@FXML
	TextField txtCurrency;
	@FXML
	TextField txtInitialRate;

	public Gui() {

	}

	@Override
	public void update(Observable o, Object arg) {
		updateLblCurrencyText();
		if(!exchange.isRunning()){
			loadCurrencies();
		}
	}

	public void init(Exchange ex) {
		this.exchange = ex;
		exchange.addObserver(this);
		loadCurrencies();
		updateLblCurrencyText();
		checkRunning();
	}

	@FXML
	protected void onAdd(ActionEvent event) {
		exchange.addCurrency(txtCurrency.getText(),
				Double.parseDouble(txtInitialRate.getText())); // Parsen nicht
																// überprüft
		txtCurrency.setText("");
		txtInitialRate.setText("");
	}

	@FXML
	protected void onStart(ActionEvent event) {
		exchange.start();
		checkRunning();
	}

	@FXML
	protected void onStop(ActionEvent event) {
		exchange.stop();
		checkRunning();
	}

	@FXML
	protected void onSelect(ActionEvent event) {
		exchange.setCurrency(cbCurrencies.getValue());
		updateLblCurrencyText();
	}

	private void loadCurrencies() {
		cbCurrencies.getItems().clear();
		cbCurrencies.getItems().addAll(exchange.getCurrencies());
		cbCurrencies.setValue(exchange.getCurrency());
	}

	private void checkRunning() {
		boolean isExchangeRunning = exchange.isRunning();
		btnStart.setDisable(isExchangeRunning);
		btnStop.setDisable(!isExchangeRunning);
		btnAdd.setDisable(isExchangeRunning);
	}

	private void updateLblCurrencyText() {
		Platform.runLater(() -> {
			lblCurrency.setText(exchange.getCurrency() + ": "
					+ exchange.getRate());
		});
	}
}
