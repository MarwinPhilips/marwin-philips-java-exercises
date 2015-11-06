package wuerfel.isA.uses;

import java.util.Arrays;
import java.util.List;

import wuerfel.Dice;

public class StatisticalDice implements Dice {
	private Dice d;
	
	private int[] stats;
	public StatisticalDice(Dice d) throws Exception{
		if (d==null)throw new Exception("The dice is not allowed to be null!");
		this.d=d;
		stats=new int[d.getMaxValue()-d.getMinValue()+1];
	}
	@Override
	public void play(){
		d.play();
		stats[d.getActualValue()-d.getMinValue()]++;
	}
	public int[] getStatistics(){
		return Arrays.copyOf(stats,stats.length);
	}
	@Override
	public int getActualValue() {
		return d.getActualValue();
	}
	@Override
	public int getMinValue() {
		return d.getMinValue();
	}
	@Override
	public int getMaxValue() {
		return d.getMaxValue();
	}
	@Override
	public List<Integer> getValues() {
		return d.getValues();
	}
}
