package ch.bfh.btx8052.test_hs14_final.gui;

import ch.bfh.btx8052.test_hs14_final.model.implementation.HaemodynAnometerModel;
import ch.bfh.btx8052.test_hs14_final.model.implementation.PersistingHaemodynAnometerModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		
		PersistingHaemodynAnometerModel ham = new PersistingHaemodynAnometerModel();
		FXMLLoader loader = new FXMLLoader(getClass().getResource(
				"GUI.fxml"));
		// Create the node hierarchy by calling load
		Parent root = (Parent) loader.load();
		// Pass the model to the controller by callng init
		loader.<Controller>getController().init(ham);

		Scene scene = new Scene(root);

		// scene.getStylesheets().add(
		// getClass().getResource("style.css").toExternalForm());
		stage.setTitle("schlussprüfung");
		stage.setScene(scene);
		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
