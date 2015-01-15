package bfh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Exchange ex = new Exchange();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(
				"ExchangeGUI.fxml"));
		// Create the node hierarchy by calling load
		Parent root = (Parent) loader.load();
		// Pass the model to the controller by callng init
		loader.<Gui>getController().init(ex);

		Scene scene = new Scene(root);

		// scene.getStylesheets().add(
		// getClass().getResource("style.css").toExternalForm());
		stage.setTitle("Exchange Watcher");
		stage.setScene(scene);
		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}