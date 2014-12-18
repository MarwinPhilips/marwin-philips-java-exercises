package udpChatWithGUI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Scanner;

public class ChatModel extends Observable {
	Chat chat;
	private StringBuilder textReceived;

	public ChatModel() {
		try {
			this.chat = new UDPChat();
		} catch (IOException e) {
			e.printStackTrace();
		}
		textReceived = new StringBuilder();
		Thread receiver = new Thread(new Receiver());
		receiver.setDaemon(true);
		receiver.start();
	}

	public void send(String text) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(baos);
		pw.write(text);
		pw.close();
		byte[] bytes = baos.toByteArray();

		try {
			chat.send(bytes);
		} catch (IOException e) {
			System.out.println("Sorry, could not send the message...");
		}
	}

	public String getText() {
		return textReceived.toString();
	}

	class Receiver implements Runnable {

		@Override
		public void run() {
			while (true) {
				ByteArrayInputStream bis;
				try {
					bis = new ByteArrayInputStream(chat.receive());
					Scanner scanner = new Scanner(bis);
					while (scanner.hasNextLine()) {
						String nextLine = scanner.nextLine();
						try {
							// String received = checkReceived(nextLine);
							textReceived.append("\n");
							textReceived.append(nextLine);
							setChanged();
							notifyObservers();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (IOException e) {
					System.out
							.println("Oh, an exception occured... let's go on...("
									+ e.toString() + ")");
				}
			}

		}

	}
}
