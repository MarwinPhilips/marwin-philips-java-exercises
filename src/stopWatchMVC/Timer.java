package stopWatchMVC;

import javafx.beans.property.StringProperty;

public class Timer implements Runnable {
	private int millisToWait;
	private int ticks = 0;
	private Thread t;
	private StopWatch gui;
	
	public Timer(int millisToWait, StopWatch gui) {
		this.millisToWait = millisToWait;
		this.gui = gui;
	}

	@Override
	public void run() {
		while (t != null) {
			
			updateGui();
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
		updateGui();
	}

	public void reset() {
		ticks = 0;
		updateGui();
	}

	public void updateGui() {		
		gui.update(getTimeString());
	}

	private int getTimeInMillis() {
		return ticks * millisToWait;
	}

	private String getTimeString() {
		int timeInMillis = getTimeInMillis();
		return (timeInMillis / 1000) + ":" + (timeInMillis % 1000);
	}
	
	public boolean isRunning(){
		return t!=null;
	}
}
