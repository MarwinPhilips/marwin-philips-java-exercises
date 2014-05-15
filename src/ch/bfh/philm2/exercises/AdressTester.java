package ch.bfh.philm2.exercises;

import ch.bfh.philm2.helpers.H;

public class AdressTester {
	public AdressTester(){
		Adress a = new Adress();
		a.setName("Philips");
		a.setPrename("Marwin");
		a.setStreet("Neuhausweg");
		a.setStreetnumber("23");
		a.setPlz(3027);
		a.setCity("Bern");
		H.pL(a.toString());
	}
}
