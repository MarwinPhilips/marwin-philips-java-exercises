package ch.bfh.philm2.exercises;

import ch.bfh.philm2.util.DueDateCalculator;

public class DueDateTester {
	public DueDateTester(){
		@SuppressWarnings("unused")
		DueDateCalculator ddc = new DueDateCalculator(2010,11,21);
		ddc = new DueDateCalculator(2011,4,7);
		ddc = new DueDateCalculator(2011,2,9);
		ddc = new DueDateCalculator(2011,1,9);
		ddc = new DueDateCalculator(2009,5,8);
	}
}
