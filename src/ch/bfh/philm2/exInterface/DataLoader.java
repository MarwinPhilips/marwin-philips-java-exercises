package ch.bfh.philm2.exInterface;

import java.util.ArrayList;

import ch.bfh.philm2.exercises.Adress;

/**
 * A class providing a list of <code>Doctor</code> and <code>Patient</code>
 * objects for test purposes.
 *
 * @author marcel.pfahrer@bfh.ch
 * @author hans.roethlisberger@bfh.ch
 * @version V26.12.2013
 */
public class DataLoader {
	private ArrayList<Person> persons;

	/**
	 * Constructs a <code>DataLoader</code> object and defines the date by
	 * filling an <code>ArrayList</code>.
	 */
	public DataLoader() {
		persons = new ArrayList<>();

		// Populate the list
		persons.add(new Doctor("Petra", "Meier", "Dr. med.", "071 717 71 17"));
		persons.add(new Doctor("Holger", "Gerber", "Prof. Dr. med.",
				"033 333 33 33"));
		persons.add(new Patient("Hans", "Muster", 10003, new Adress(
				"Hintergasse", "5", "3012", "Bern")));
		persons.add(new Patient("Georg", "Meier", 10004, new Adress(
				"Vordergasse", "15", "3012", "Bern")));
		persons.add(new Patient("Heinrich", "Moser", 10005, new Adress(
				"Irgendwo", "12", "2504", "Biel")));
		persons.add(new Doctor("Georg F.", "Strahlemann", "Dr. med.",
				"099 837 98 92"));
		persons.add(new Doctor("Helga", "Moser", "PD Dr. med.", "099 929 99 99"));
		persons.add(new Patient("Johnny", "Black", 10006, new Adress(
				"Schnellstrasse", "99", "3012", "Bern")));
		persons.add(new Patient("Peter", "White", 10007, new Adress("Hinterweg",
				"24", "2501", "Biel")));
		persons.add(new Patient("Sam", "Brown", 10008, new Adress("Vorderweg",
				"8", "2550", "Nidau")));
		persons.add(new Patient("Sally", "Field", 10009, new Adress("Baumallee",
				"2", "3012", "Bern")));
		persons.add(new Patient("Gerold", "Habakuk", 10010, new Adress(
				"Uferweg", "22", "2555", "Brügg")));
	}

	/**
	 * Returns an <code>ArrayList</code> with the defined <code>Doctor</code> and
	 * <code>Patient</code> objects.
	 *
	 * @return The <code>ArrayList</code> with <code>Doctor</code> and
	 *         <code>Patient</code> objects.
	 */
	public ArrayList<Person> getData() {
		return persons;
	}
}
