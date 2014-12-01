package programmiermethodenTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestAlarmJUnit {
	Alarm a;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		a = new TwitterAlarm(); 
	}

	@Test
	public void testIsActive() {
		assertTrue("Should not be active",a.isActivated() == false);
		a.activate(true);
		assertTrue("Should be active",a.isActivated());
	}
	@Test
	public void testAlarmTime(){
		assertTrue("Should be 0",a.getAlarmTime()==0);
		try{
			a.setAlarm(System.currentTimeMillis()-100);
			fail("should throw Exception becaus time is in the past");
		}catch(Exception ex){
			
		}
		long timeSet = System.currentTimeMillis()+1000;
		try{
			a.setAlarm(timeSet);
		}catch(Exception ex){
			fail("Should not throw an Exception, time is in Future");
		}
		assertTrue("Times should be the same",a.getAlarmTime()==timeSet);
	}
	@Test
	public void testAlarmActivation(){
		a.setAlarm(System.currentTimeMillis()+1000);
		a.activate(true);
		int waitedSeconds = 0;
		while(a.isActivated()){
			try {
				Thread.sleep(1001); // 1001 damit sicher nach 1 Sekunde der Wecker geklingelt haben muss.
			} catch (InterruptedException e) {
				fail("Could not sleep for 1 second");
			}
			waitedSeconds++;			
			if(waitedSeconds > 10) {
				fail("Endless loop possible!");
				break;
			}
		}
		assertTrue("Alarm should be deactivated after 1 Second",waitedSeconds==1);
	}
}
