package wuerfel;

public class FairDice extends aDice {
	public FairDice(int minValue, int maxValue) throws Exception{
		this.maxValue=maxValue;
		this.minValue=minValue;
		checkInitialValues();
		play();
	}

}
