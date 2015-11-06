package udpChat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Scanner;

import wuerfel.Dice;
import wuerfel.FairDice;
import wuerfel.hasA.looselyCoupled.Statistic;

/**
 *
 * @author Reto E. Koenig <reto.koenig@bfh.ch>
 */
public class UDPChatTest {

	public static final int PORT = 6543;

	public static final String IPv6MCAST_ADDR = "FF7E:230::1234";
	public static final String IPv4MCAST_ADDR = "224.0.1.0";
	public static final String IP_MCAST_ADDR = IPv4MCAST_ADDR;
	public static final String IP_ADDRESS = "147.87.33.187";// Example:192.168.1.123

	public static void main(String[] args) {
		try {
			//Enumeration<NetworkInterface> ifs = NetworkInterface
			//		.getNetworkInterfaces();

			String Line = "";

			InetAddress group = InetAddress.getByName(IP_MCAST_ADDR);
			MulticastSocket socket = new MulticastSocket(PORT);
			socket.setInterface(InetAddress.getByName(IP_ADDRESS));
			socket.joinGroup(group);

			ChatListener objChatListener = new ChatListener();
			Thread ChatListenerThread = new Thread(objChatListener);
			ChatListenerThread.setDaemon(true);// Stops other threads when main
												// is about to exit
			ChatListenerThread.start();

			System.out
					.println("Welcome to the Dicerollermachine!"
							+ "\nStart by entering minValue and maxValue.");

			Scanner scanner = new Scanner(System.in);
			while (scanner.hasNextLine()) {
				Line = scanner.nextLine();
				if (Line.equals("quit")) {
					break;
				}
				DatagramPacket msg = new DatagramPacket(Line.getBytes(),
						Line.length(), group, PORT);
				socket.send(msg);
			}
			scanner.close();
			socket.leaveGroup(group);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ChatListener implements Runnable {
	Dice d;
	int minInsertedVal;
	int maxInsertedVal;
	Statistic stat;

	public void run() {
		String received;
		try {
			InetAddress group = InetAddress.getByName(UDPChatTest.IP_MCAST_ADDR);
			MulticastSocket socket = new MulticastSocket(UDPChatTest.PORT);
			socket.setInterface(InetAddress.getByName(UDPChatTest.IP_ADDRESS));
			socket.joinGroup(group);
			while (true) {
				byte[] buf = new byte[1000];
				DatagramPacket recv = new DatagramPacket(buf, buf.length);
				socket.receive(recv);
				received = new String(recv.getData()).trim();
				received = checkReceived(received);

				System.out.println("Received -> " + received);
				received = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String checkReceived(String received) throws Exception {
		boolean orderKnown = false;
		if (d == null) {
			if (received.startsWith("minValue ")) {
				minInsertedVal = Integer.parseInt(received.substring(9,
						received.length()));
				orderKnown=true;
				
			} else if (received.startsWith("maxValue ")) {
				maxInsertedVal = Integer.parseInt(received.substring(9,
						received.length()));
				orderKnown=true;
			}
		} else {
			if (received.startsWith("play")) {
				d.play();
				stat.updateStatistics(d.getActualValue(), minInsertedVal, maxInsertedVal);
				received = "Dice was rolled";
				orderKnown=true;
			} else if (received.startsWith("read")) {
				received = "Value: " + d.getActualValue();
				orderKnown=true;
			} else if (received.startsWith("stats")){
				StringBuilder printOut =new StringBuilder();
				int[] stats= stat.getStatistics();
				for(int i=0;i<stats.length;i++){
					printOut.append(d.getMinValue()+i);
					printOut.append(": ");
					printOut.append(stats[i]);
					printOut.append("; ");
				}
				received = printOut.toString();
				orderKnown=true;
			}
		}
		if (maxInsertedVal != 0 && minInsertedVal != 0 && d == null) {
			try {
				d = new FairDice(minInsertedVal, maxInsertedVal);
				stat = new Statistic(minInsertedVal,maxInsertedVal);
				stat.updateStatistics(d.getActualValue(), minInsertedVal, maxInsertedVal);
				System.out.println("Your Dice and Stats have been initialized!\n"
						+ "You can use now use play, read and stats.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!orderKnown){
			received = "The order is not known or used in the wrong context!";
		}
		return received;
	}
}
