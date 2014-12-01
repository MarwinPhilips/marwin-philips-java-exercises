package würfel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class aDice implements Dice{
	protected int actualValue;
	protected int maxValue;
	protected int minValue;
	protected static final Random random;
	static{
		random = new Random();
	}
	
	@Override
	public void play() {
		actualValue=random.nextInt(maxValue-minValue+1)+minValue;		
	}

	@Override
	public int getActualValue() {
		return actualValue;
	}

	@Override
	public int getMinValue() {
		return minValue;
	}

	@Override
	public int getMaxValue() {
		return maxValue;
	}

	@Override
	public List<Integer> getValues() {
		List<Integer> ret = new ArrayList<Integer>(maxValue-minValue+1);
		for(int i = minValue;i<=maxValue;i++)
			ret.add(i);
		return ret;
	}
	protected void checkInitialValues() throws Exception{
		if(maxValue<minValue)
			throw new Exception("maxValue darf nicht kleiner sein als minValue");
		if(maxValue==minValue)
			throw new Exception("maxValue darf nicht gleich sein wie minValue");
		if(maxValue<0 || minValue<0)
			throw new Exception("Ein FairDice kann keine Negativen Zahlen würfeln");
	}
}
