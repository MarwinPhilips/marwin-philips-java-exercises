package programmiermethodenTest;

public abstract class AnAlarm implements Alarm{
	private boolean isActivated;
	private long alarmTime;

	@Override
	public void activate(boolean isActivated) {
		this.isActivated = isActivated;
	}

	@Override
	public long getAlarmTime() {
		return alarmTime;
	}

	@Override
	public boolean isActivated() {
		return isActivated;
	}

	@Override
	public void setAlarm(long alarmTimeInMillis)
			throws IllegalArgumentException {
		if (alarmTimeInMillis < System.currentTimeMillis()) {
			throw new IllegalArgumentException();
		}
		this.alarmTime = alarmTimeInMillis;
	}

	protected void alarmIfActivatedAndTimeReached(long timeInMillis) {
		if (this.isActivated && this.alarmTime <= timeInMillis) {
			this.isActivated = false;
			new Thread(getAlarmRunnable()).start();
		}
	}

	protected abstract Runnable getAlarmRunnable();
}
