package würfel.hasA.looselyCoupled;

import java.util.Arrays;


public class Statistic implements Statistics {
	private int minimal;
	private int maximal;
	private int[] stats;
	
	public Statistic(int minimal, int maximal) throws Exception{
		this.minimal=minimal;
		this.maximal=maximal;
		checkInitialValues();
		stats=new int[maximal-minimal+1];
	}
	@Override
	public void updateStatistics(int actual, int minimal, int maximal) throws Exception {
		if(this.minimal!=minimal || maximal!= maximal) throw new Exception("max or min is not the same as initialized!");
		this.stats[actual-minimal]++;
	}

	@Override
	public int[] getStatistics() {
		return Arrays.copyOf(stats,stats.length);
	}
	
	protected void checkInitialValues() throws Exception{
		if(maximal<minimal)
			throw new Exception("maxValue darf nicht kleiner sein als minValue");
		if(maximal==minimal)
			throw new Exception("maxValue darf nicht gleich sein wie minValue");
		if(maximal<0 || minimal<0)
			throw new Exception("Eine Statistik ist nur für positive Zahlen erlaubt!");
	}
}
