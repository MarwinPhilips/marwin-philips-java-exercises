package w�rfel;

import java.util.List;

public interface Dice {
	public void play();
	public int getActualValue();
	public int getMinValue();
	public int getMaxValue();
	public List<Integer> getValues();
}
