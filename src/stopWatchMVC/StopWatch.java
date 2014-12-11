package stopWatchMVC;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StopWatch extends Stage implements Observer {
	private Timer timer;
	private Label lblSekunden;
	private Label lblZeit;
	private Stage parentStage;

	public StopWatch(Timer timer, Stage parentStage) {
		this.parentStage=parentStage;
		this.timer= timer;
		timer.addObserver(this);
		CreateGui();
	}

	private void CreateGui() {
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp, 150, 50);
		setScene(scene);
		lblSekunden = new Label("Sekunden: ");
		lblZeit = new Label("0:00");
		HBox centerBox = new HBox(20);
		bp.setCenter(centerBox);
		centerBox.getChildren().add(lblSekunden);
		centerBox.getChildren().add(lblZeit);
		initOwner(parentStage);
		show();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		String timeString = timer.getTimeString();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				lblZeit.setText(timeString);
			}
		});
	}

}
