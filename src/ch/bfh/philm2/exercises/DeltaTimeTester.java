package ch.bfh.philm2.exercises;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.philm2.helpers.H;
import ch.bfh.philm2.util.DeltaTime;

public class DeltaTimeTester {
	public DeltaTimeTester(){
		List<DeltaTime> deltaTimes = new ArrayList<DeltaTime>();
		deltaTimes.add(new DeltaTime(2011,1,1,2013,8,2));
		deltaTimes.add(new DeltaTime(2011,1,1,2012,1,1));
		deltaTimes.add(new DeltaTime(1970,1,1,1969,1,1));
		deltaTimes.add(new DeltaTime(2013,8,2,2013,1,2));
		//deltaTimes.stream().
		//filter(x->x.getDeltaTimeInDays()==365).forEach(x->x.getDeltaTimeInYearsMonthsDays());
		deltaTimes.forEach(x-> H.pL(x.getDeltaTimeInYearsMonthsDays()));
//		for(DeltaTime t : deltaTimes){
//			t.getDeltaTimeInYearsMonthsDays();
//		}
	}
}
