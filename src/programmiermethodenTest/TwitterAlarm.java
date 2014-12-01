package programmiermethodenTest;

import java.io.OutputStream;
import java.io.PrintWriter;

public class TwitterAlarm extends AnAlarm {

	private boolean isRinging = false;

	public TwitterAlarm() {
		Thread t1 = new Thread(new CheckAlarmIfActivatedAndTimeReached());
		t1.setDaemon(true);
		t1.start();
	}

	@Override
	protected Runnable getAlarmRunnable() {
		return new Ringer("ringring", System.out);
	}

	@Override
	protected void alarmIfActivatedAndTimeReached(long timeInMillis) {
		if (isActivated() && getAlarmTime() <= timeInMillis) {
			activate(false);
			new Thread(getAlarmRunnable()).start();
			isRinging = true;
		}
	}

	class Ringer implements Runnable {
		PrintWriter w;
		String text;

		public Ringer(String text, OutputStream os)
				throws IllegalArgumentException {
			if (os == null)
				throw new IllegalArgumentException(
						"Outputstream must not be null");
			w = new PrintWriter(os);
			this.text = text;
		}

		@Override
		public void run() {
			w.print(text);
			w.flush();
		}
	}

	class CheckAlarmIfActivatedAndTimeReached implements Runnable {
		@Override
		public void run() {
			while (!TwitterAlarm.this.isRinging) {
				TwitterAlarm.this.alarmIfActivatedAndTimeReached(System.currentTimeMillis());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
