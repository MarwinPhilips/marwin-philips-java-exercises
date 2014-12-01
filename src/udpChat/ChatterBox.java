package udpChat;

import java.io.IOException;

public class ChatterBox {
	public static void main(String[] args) throws IOException {
		UDPChat chat = new UDPChat();
		Thread netToConsole = new Thread(new NetToConsoleRunnable(chat));
		Thread consoleToNet = new Thread(new ConsoleToNetRunnable(chat));
		netToConsole.setDaemon(true);
		netToConsole.start();
		consoleToNet.start();
		try {
			consoleToNet.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class NetToConsoleRunnable implements Runnable {
	private final Chat chat;

	public NetToConsoleRunnable(Chat chat) {
		this.chat = chat;
	}

	@Override
	public void run() {
		NetToConsole netToConsole = new NetToConsole(chat);
		netToConsole.infiniteRead();
	}
}

class ConsoleToNetRunnable implements Runnable {
	private final Chat chat;

	public ConsoleToNetRunnable(Chat chat) {
		this.chat = chat;
	}

	@Override
	public void run() {
		ConsoleToNet consoleToNet = new ConsoleToNet(chat);
		consoleToNet.infiniteRead();
	}

}