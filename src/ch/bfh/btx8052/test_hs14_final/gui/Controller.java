package ch.bfh.btx8052.test_hs14_final.gui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ch.bfh.btx8052.test_hs14_final.model.definition.BloodPressureSample;
import ch.bfh.btx8052.test_hs14_final.model.enumerations.Status;
import ch.bfh.btx8052.test_hs14_final.model.enumerations.StatusIntention;
import ch.bfh.btx8052.test_hs14_final.model.implementation.HaemodynAnometerModel;
import ch.bfh.btx8052.test_hs14_final.model.implementation.PersistingHaemodynAnometerModel;

public class Controller implements Observer {

	@FXML
	Label lblPuls;
	@FXML
	Label lblSystolic;
	@FXML
	Label lblDiastolic;
	@FXML
	Button btnPause;
	@FXML
	Button btnRun;
	@FXML
	Button btnStop;
	private PersistingHaemodynAnometerModel model;
	long nextUpdate;
	@FXML Button btnPersist;
	@FXML Button btnLoad;
	
	ByteArrayOutputStream baos;
	protected void init(PersistingHaemodynAnometerModel ham) {
		this.model = ham;
		model.addObserver(this);
		update(null,null);
	}

	@FXML
	public void onRun(ActionEvent event) {
		model.setStatusIntention(StatusIntention.Run);
	}

	@FXML
	public void onPause(ActionEvent event) {
		model.setStatusIntention(StatusIntention.Pause);
	}

	@FXML
	public void onStop(ActionEvent event) {
		model.setStatusIntention(StatusIntention.Stop);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (nextUpdate < System.currentTimeMillis()
				|| model.getLatestStatus() == Status.Finished || model.getLatestStatus() == Status.Stopped) {
			nextUpdate = System.currentTimeMillis() + 1000;
			BloodPressureSample sample = model.getLatestBloodPressureSample();
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					setSampleText(sample);
					boolean isRunning = model.getLatestStatus() == Status.Running;
					btnPause.setDisable(!isRunning);
					btnStop.setDisable(!isRunning);
					btnRun.setDisable(isRunning);
				}
			});
		}

	}
	
	@FXML public void onPersist(ActionEvent event) {
		baos = new ByteArrayOutputStream();
		model.persistLatestBloodPressureSample(baos);		
	}

	@FXML public void onLoad(ActionEvent event) {
		 ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		    BloodPressureSample lastBlood = model.retrieveBloodPressureSample(bais);
		setSampleText(lastBlood);
	}
	
	private void setSampleText(BloodPressureSample sample){
		if (sample == null) {
			lblDiastolic.setText("0");
			lblPuls.setText("0");
			lblSystolic.setText("0");
		} else {
			lblDiastolic.setText("" + sample.getDiastolicInMMHg());
			lblPuls.setText("" + sample.getPulse());
			lblSystolic.setText("" + sample.getSystolicInMMHg());
		}
	}
}
