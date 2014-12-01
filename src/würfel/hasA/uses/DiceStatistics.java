package würfel.hasA.uses;

import java.util.Arrays;

import würfel.Dice;

public class DiceStatistics {
	private Dice d;
	private int[] stats;
	public DiceStatistics(Dice d) throws Exception{
		if(d==null)throw new Exception("Dice must not be null!");
		this.d=d;
		stats=new int[d.getMaxValue()-d.getMinValue()+1];
	}
	public void updateStatistics(){
		this.stats[d.getActualValue()-d.getMinValue()]++;
	}
	public int[] getStatistics(){
		return Arrays.copyOf(stats,stats.length);
	}
}
