package wuerfel.isA.ext;

import java.util.Arrays;

import wuerfel.aDice;

public class StatisticalDice extends aDice {
	private int[] stats;
	public StatisticalDice(int minValue, int maxValue) throws Exception{
		this.minValue=minValue;
		this.maxValue=maxValue;
		checkInitialValues();
		stats=new int[maxValue-minValue+1];
	}
	@Override
	public void play(){
		super.play();
		stats[actualValue-minValue]++;
	}
	public int[] getStatistics(){
		return Arrays.copyOf(stats,stats.length);
	}
}
