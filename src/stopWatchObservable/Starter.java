package stopWatchObservable;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Starter extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		StopWatch root = new StopWatch();
		Scene scene = new Scene(root, 250, 250);
		primaryStage.setScene(scene);		
		primaryStage.show();		
	}
	
	public static void main(String[]args){
		launch(args);
	}
}
