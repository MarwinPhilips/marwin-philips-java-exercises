package wuerfel.isA.uses;

import wuerfel.FairDice;
import wuerfel.isA.uses.StatisticalDice;

public class StatisticalDiceTester {
	private static int maxInsertedVal=1000;
	private static int minInsertedVal=100;
	public static void main(String[] args) {
		StatisticalDice sd;
		try{
			sd=new StatisticalDice(new FairDice(minInsertedVal,maxInsertedVal));
			for(int i =0;i<1000000;i++){
				sd.play();
			}
			StringBuilder printOut =new StringBuilder();
			int[] stats= sd.getStatistics();
			for(int i=0;i<stats.length;i++){
				printOut.append(sd.getMinValue()+i +": "+stats[i] +"; ");
			}
			System.out.println(printOut);
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage()+ex.getStackTrace());
		}
	}
}
