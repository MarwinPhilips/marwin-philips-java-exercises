package würfel;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class DiceTester {
	private final static int maxInsertedValue=100;
	private final static int minInsertedValue=1;
	public static void main(String[] args){
		
		try{
			Dice d = new FairDice(minInsertedValue, maxInsertedValue);
			testDice(d);
		}
		catch(Exception e){
			System.out.println("Message: "+e.getMessage());
			System.out.println("StackTrace: "+e.getStackTrace());
		}
	}
	public static void testDice(Dice dice) {
		final int minValue = dice.getMinValue();
		final int maxValue = dice.getMaxValue();
		final int actualValue = dice.getActualValue();
		Assert.assertEquals("minValue not minInsertedValue", minValue, minInsertedValue);
		Assert.assertNotEquals("minValue==maxValue ("
		+ "max: " + maxValue + " min: " + minValue + ")",maxValue,minValue);
		Assert.assertFalse("maxValue<minValue ("
				+ "max: " + maxValue + " min: " + minValue + ")",minValue>maxValue);
		
		{ // Test maxValue >= actualValue >= minValue
			if (minValue > actualValue || maxValue < actualValue)
				throw new IllegalArgumentException(
						"actualValue beyond allowed value range (" + "max: "
								+ maxValue + " min: " + minValue + " act: "
								+ actualValue + ")");
		}
		{ // Test for idempotence
			for (int i = 0; i < 1000; i++) {
				int newMinValue = dice.getMinValue();
				int newMaxValue = dice.getMaxValue();
				int newActualValue = dice.getActualValue();
				if (newMinValue != minValue || newMaxValue != maxValue
						|| newActualValue != actualValue)
					throw new IllegalArgumentException(
							"getter methods not idempotent");
			}
		}

		{ // Test if play / getActualValue only present values within the range
			// of [MinValue,MaxValue]
			int range = Math.abs(maxValue - minValue);
			for (int i = 0; i < range * 1000; i++) {
				if (dice.getActualValue() > dice.getMaxValue()
						|| dice.getActualValue() < dice.getMinValue())
					throw new IllegalArgumentException("Value out of range ("
							+ "max: " + dice.getMaxValue() + " min: "
							+ dice.getMinValue() + " act: "
							+ dice.getActualValue() + ")");
			}
		}

		{ // Test if play reaches all values within the range of
			// [MinValue,MaxValue]
			int range = Math.abs(dice.getMaxValue() - dice.getMinValue());
			int[] valueRange = new int[range + 1];
			for (int i = 0; i < range * 1000; i++) {
				valueRange[Math.abs(dice.getActualValue() - dice.getMinValue())]++;
				dice.play();
			}
			for (int i : valueRange)
				if (i == 0)
					throw new IllegalArgumentException(
							"Not all values of the range are covered ("
									+ Arrays.toString(valueRange) + ")");
		}
	}
}
