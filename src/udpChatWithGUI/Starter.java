package udpChatWithGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		ChatModel model = new ChatModel();
		// Create an FXMLLoader instance based on the FXML (instead of just
		// calling
		// the static load function)
		FXMLLoader loader = new FXMLLoader(getClass().getResource(
				"ChatGUI.fxml"));
		// Create the node hierarchy by calling load
		Parent root = (Parent) loader.load();
		// Pass the model to the controller by callng init
		loader.<ChatController> getController().init(model);

		Scene scene = new Scene(root);
		// scene.getStylesheets().add(
		// getClass().getResource("style.css").toExternalForm());
		stage.setTitle("FXML Demo");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
