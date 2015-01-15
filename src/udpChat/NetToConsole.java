package udpChat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

import würfel.Dice;
import würfel.FairDice;
import würfel.hasA.looselyCoupled.Statistic;

public class NetToConsole {
	private Chat chat;
	Dice d;
	int minInsertedVal;
	int maxInsertedVal;
	Statistic stat;

	public NetToConsole(Chat chat) {
		if (chat == null)
			throw new IllegalArgumentException();
		this.chat = chat;
	}

	public void infiniteRead() {
		while (true) {
			ByteArrayInputStream bis;
			try {
				bis = new ByteArrayInputStream(chat.receive());
				Scanner scanner = new Scanner(bis);
				while (scanner.hasNextLine()) {
					String nextLine = scanner.nextLine();
					try {
						String received = checkReceived(nextLine);
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

	private String checkReceived(String received) throws Exception {
		boolean orderKnown = false;
		if (d == null) {
			if (received.startsWith("minValue ")) {
				minInsertedVal = Integer.parseInt(received.substring(9,
						received.length()));
				orderKnown = true;

			} else if (received.startsWith("maxValue ")) {
				maxInsertedVal = Integer.parseInt(received.substring(9,
						received.length()));
				orderKnown = true;
			}
		} else {
			if (received.startsWith("play")) {
				d.play();
				stat.updateStatistics(d.getActualValue(), minInsertedVal,
						maxInsertedVal);
				received = "Dice was rolled";
				orderKnown = true;
			} else if (received.startsWith("read")) {
				received = "actValue: Value: " + d.getActualValue();
				orderKnown = true;
			} else if (received.startsWith("stats")) {
				StringBuilder printOut = new StringBuilder();
				int[] stats = stat.getStatistics();
				for (int i = 0; i < stats.length; i++) {
					printOut.append(d.getMinValue() + i);
					printOut.append(": ");
					printOut.append(stats[i]);
					printOut.append("; ");
				}
				received = printOut.toString();
				orderKnown = true;
			}
		}
		if (maxInsertedVal != 0 && minInsertedVal != 0 && d == null) {
			try {
				d = new FairDice(minInsertedVal, maxInsertedVal);
				stat = new Statistic(minInsertedVal, maxInsertedVal);
				stat.updateStatistics(d.getActualValue(), minInsertedVal,
						maxInsertedVal);
				System.out
						.println("Your Dice and Stats have been initialized!\n"
								+ "You can use now use play, read and stats.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!orderKnown) {
			received = "The order is not known or used in the wrong context!";
		}
		return received;
	}
}
