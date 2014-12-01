package programmiermethodenTest;

public interface Alarm {
	public void setAlarm(long timeStamp) throws IllegalArgumentException;

	public void activate(boolean isActivated);

	public long getAlarmTime();

	public boolean isActivated();
}
