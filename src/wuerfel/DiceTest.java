package wuerfel;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import wuerfel.isA.ext.StatisticalDice;

public class DiceTest {
	private static int minSetValue = 1;
	private static int maxSetValue = 1000;
	private Dice d;
	private int maxValue;
	private int minValue;
	private StatisticalDice sd;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		sd=new StatisticalDice(minSetValue,maxSetValue);
		d = new FairDice(minSetValue, maxSetValue);
		minValue = d.getMinValue();
		maxValue = d.getMaxValue();
	}

	@Test
	public void testGetter() {		
		// Are the inserted Values valid?
		Assert.assertNotEquals("minValue==maxValue (" + "max: " + maxValue
				+ " min: " + minValue + ")", maxValue, minValue);
		Assert.assertFalse("maxValue<minValue (" + "max: " + maxValue
				+ " min: " + minValue + ")", minValue > maxValue);
		// is there a current played value?
		Assert.assertTrue(d.getActualValue() != 0);
	}

	@Test
	public void testPlay() {
		// Test if play / getActualValue only present values within the range
		// of [MinValue,MaxValue]
		int range = Math.abs(maxValue - minValue);
		for (int i = 0; i < range * 1000; i++) {
			assertFalse(
					"Value out of range (" + "max: " + d.getMaxValue()
							+ " min: " + d.getMinValue() + " act: "
							+ d.getActualValue() + ")",
					d.getActualValue() > d.getMaxValue()
							|| d.getActualValue() < d.getMinValue());
		}
		// Test if play reaches all values within the range of
		// [MinValue,MaxValue]
		range = Math.abs(d.getMaxValue() - d.getMinValue());
		int[] valueRange = new int[range + 1];
		for (int i = 0; i < range * 1000; i++) {
			valueRange[Math.abs(d.getActualValue() - d.getMinValue())]++;
			d.play();
		}
		for (int i : valueRange)
			Assert.assertFalse("Not all values of the range are covered ("
					+ Arrays.toString(valueRange) + ")", i == 0);
	}

	@Test
	public void testGetActualValue() {
		int actualValue = d.getActualValue();
		Assert.assertFalse(
				"actualValue beyond allowed value range (" + "max: " + maxValue
						+ " min: " + minValue + " act: " + actualValue + ")",
				minValue > actualValue || maxValue < actualValue);
	}

	@Test
	public void testGetValues() {
		int actualValue = d.getActualValue();
		for (int i = 0; i < 1000; i++) {
			int newMinValue = d.getMinValue();
			int newMaxValue = d.getMaxValue();
			int newActualValue = d.getActualValue();
			Assert.assertFalse("getter methods not idempotent",
					newMinValue != minValue || newMaxValue != maxValue
							|| newActualValue != actualValue);
		}
	}
	@Test
	public void testGetStatistics(){
		for(int i = 0; i<1000;i++)
			sd.play();
	}
}
