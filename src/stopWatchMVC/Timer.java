package stopWatchMVC;

import java.util.Observable;


public class Timer extends Observable implements Runnable {
	private int millisToWait;
	private int ticks = 0;
	private Thread t;
	
	public Timer(int millisToWait) {
		this.millisToWait = millisToWait;
	}

	@Override
	public void run() {
		while (t != null) {			
			doNotify();
			ticks++;
			try {
				Thread.sleep(millisToWait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.setDaemon(true);
			t.start();
		}
	}

	public void stop() {
		t = null;
		doNotify();
	}

	public void reset() {
		ticks = 0;
		doNotify();
	}
	private void doNotify(){
		setChanged();
		notifyObservers();
	}

	private int getTimeInMillis() {
		return ticks * millisToWait;
	}

	public String getTimeString() {
		int timeInMillis = getTimeInMillis();
		return (timeInMillis / 1000) + ":" + (timeInMillis % 1000);
	}
	
	public boolean isRunning(){
		return t!=null;
	}

}
