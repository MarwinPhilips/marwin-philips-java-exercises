package stopWatchMVC;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StopWatchController extends Stage implements Observer {
	private Button btnStart;
	private Button btnStop;
	private Button btnReset;
	private Timer timer;

	public StopWatchController(Timer timer) {
		this.timer = timer;
		timer.addObserver(this);
		createGUI();

	}

	private void createGUI() {
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp, 200, 50);
		setScene(scene);
		setY(100);
		setX(100);

		HBox hbox = new HBox(20);
		bp.setCenter(hbox);
		btnStart = new Button("Start");
		btnStart.addEventHandler(ActionEvent.ACTION, event -> start());
		btnStop = new Button("Stop");
		btnStop.disableProperty().set(true);
		btnStop.addEventHandler(ActionEvent.ACTION, event -> stop());
		btnReset = new Button("Reset");
		btnReset.disableProperty().set(true);
		btnReset.addEventHandler(ActionEvent.ACTION, event -> reset());
		hbox.getChildren().add(btnStart);
		hbox.getChildren().add(btnStop);
		hbox.getChildren().add(btnReset);
		show();

	}

	private void reset() {
		timer.reset();
	}

	private void stop() {
		timer.stop();
	}

	private void start() {
		timer.start();
	}

	@Override
	public void update(Observable o, Object arg) {
		String timeString = timer.getTimeString();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (timer.isRunning()) {
					btnStart.disableProperty().set(true);
					btnStop.disableProperty().set(false);
				} else {
					btnStart.disableProperty().set(false);
					btnStop.disableProperty().set(true);
				}
				if (timeString.equals("0:0")) {
					btnReset.disableProperty().set(true);
				} else {
					btnReset.disableProperty().set(false);
				}
			}
		});

	}

}
