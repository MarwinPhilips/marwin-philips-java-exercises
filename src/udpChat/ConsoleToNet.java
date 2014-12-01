package udpChat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConsoleToNet {
	private Chat chat;

	public ConsoleToNet(Chat chat) {
		if (chat == null)
			throw new IllegalArgumentException();
		this.chat = chat;
	}

	public void infiniteRead() {
		System.out.println("Enter Message: ");
		Scanner scanner = new Scanner(System.in);
		String line = null;
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			if (line.equals("quit")) {
				return;
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(baos);
			pw.write(line);
			pw.close();
			byte[] bytes = baos.toByteArray();

			try {
				chat.send(bytes);
			} catch (IOException e) {
				System.out.println("Sorry, could not send the message...");
			}
		}
	}
}