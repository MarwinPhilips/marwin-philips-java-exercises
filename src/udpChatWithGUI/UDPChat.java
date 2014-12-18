package udpChatWithGUI;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;

public class UDPChat implements Chat {
	static {
		System.out.println("Available networks");
		try {
			Enumeration<NetworkInterface> ifs;

			ifs = NetworkInterface.getNetworkInterfaces();
			while (ifs.hasMoreElements()) {
				NetworkInterface networkInterface = ifs.nextElement();
				System.out.println(networkInterface.getName());
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	// Maximum time to live (TTL) for each Dtagram
	public static final int TTL = 2;

	// Maximum bytes to transport in a single Datagram
	public static final int MAX_MESSAGE_SIZE = 1000;

	// Multicast address in IPv4 or IPv6
	public static final String IPv6MCAST_ADDR = "FF7E:230::1234";
	public static final String IPv4MCAST_ADDR = "224.0.1.0";

	// Port assigned to this program
	public static final int PORT = 6543;

	// Name of the network on which the Multicast is established
	public static final String NETWORK_NAME = "wlan0";

	public static final String IP_MCAST_ADDR = IPv4MCAST_ADDR;

	private final MulticastSocket socket;
	private final InetAddress group;

	public UDPChat() throws IOException {
		group = InetAddress.getByName(IP_MCAST_ADDR);
		this.socket = new MulticastSocket(PORT);
		socket.setNetworkInterface(NetworkInterface.getByName(NETWORK_NAME));
		socket.setTimeToLive(TTL);
		socket.joinGroup(group);
	}

	@Override
	protected void finalize() throws Throwable {
		socket.leaveGroup(group);
		super.finalize();
	}

	@Override
	public byte[] receive() throws IOException {
		byte[] buf = new byte[UDPChat.MAX_MESSAGE_SIZE];
		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
		socket.receive(datagramPacket);
		return Arrays.copyOfRange(buf, datagramPacket.getOffset(),
				datagramPacket.getLength());
	}

	@Override
	public void send(byte[] message) throws IOException {
		if (message == null || message.length > MAX_MESSAGE_SIZE)
			throw new IllegalArgumentException();
		DatagramPacket msg = new DatagramPacket(message, message.length, group,
				PORT);
		socket.send(msg);
	}
}
