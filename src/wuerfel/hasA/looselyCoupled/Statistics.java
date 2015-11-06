package wuerfel.hasA.looselyCoupled;

public interface Statistics {
	public void updateStatistics(int actual, int minimal, int maximal) throws Exception;
	int[] getStatistics();
}
