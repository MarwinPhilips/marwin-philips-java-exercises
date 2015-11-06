package wuerfel.hasA.uses;

import wuerfel.Dice;
import wuerfel.FairDice;

public class DiceStatisticsTester {
	private static int maxInsertedVal=1000;
	private static int minInsertedVal=100;
	public static void main(String[] args) {
		Dice d;
		try{
			d=new FairDice(minInsertedVal,maxInsertedVal);
			DiceStatistics ds = new DiceStatistics(d);
			for(int i =0;i<1000000;i++){
				d.play();
				ds.updateStatistics();
			}
			StringBuilder printOut =new StringBuilder();
			int[] stats= ds.getStatistics();
			for(int i=0;i<stats.length;i++){
				printOut.append(d.getMinValue()+i +": "+stats[i] +"; ");
			}
			System.out.println(printOut);
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage()+ex.getStackTrace());
		}
	}
}
