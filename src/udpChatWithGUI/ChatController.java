package udpChatWithGUI;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ChatController implements Observer {

	private ChatModel chatModel;
	@FXML
	private TextArea txtReceivedMessages;
	@FXML
	private TextArea txtSendMessage;
	@FXML
	private Button btnSend;

	public void init(ChatModel chatmodel) {
		this.chatModel = chatmodel;
		this.chatModel.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		Platform.runLater(() -> {
			txtReceivedMessages.textProperty().set(chatModel.getText());
		});
	}

	@FXML
	protected void onSendAction(ActionEvent event) {
		chatModel.send(txtSendMessage.getText());
		txtSendMessage.setText("");
	}

}
