package javaFX;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StopWatch extends BorderPane {
	private Button btnStart;
	private Button btnStop;
	private Button btnReset;
	private Timer timer;
	private Label sekunden;
	private Label zeit;

	public StopWatch() {
		timer = new Timer(50, this);
		CreateGui();
	}

	private void CreateGui() {
		sekunden = new Label("Sekunden: ");
		zeit = new Label("0:00");
		HBox centerBox = new HBox(20);
		setCenter(centerBox);
		centerBox.getChildren().add(sekunden);
		centerBox.getChildren().add(zeit);

		HBox hbox = new HBox(20);
		setBottom(hbox);
		btnStart = new Button("Start");
		btnStart.addEventHandler(ActionEvent.ACTION, event -> timer.start());
		btnStop = new Button("Stop");
		btnStop.disableProperty().set(true);
		btnStop.addEventHandler(ActionEvent.ACTION, event -> timer.stop());
		btnReset = new Button("Reset");
		btnReset.disableProperty().set(true);
		btnReset.addEventHandler(ActionEvent.ACTION, event -> timer.reset());
		hbox.getChildren().add(btnStart);
		hbox.getChildren().add(btnStop);
		hbox.getChildren().add(btnReset);
	}

	public void update(String timeString) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				zeit.setText(timeString);
				if(timer.isRunning()){
					btnStart.disableProperty().set(true);
					btnStop.disableProperty().set(false);
				}else{
					btnStart.disableProperty().set(false);
					btnStop.disableProperty().set(true);
				}
				if(timeString.equals("0:0")){
					btnReset.disableProperty().set(true);
				}else{
					btnReset.disableProperty().set(false);
				}
			}
		});
	}
	
}
