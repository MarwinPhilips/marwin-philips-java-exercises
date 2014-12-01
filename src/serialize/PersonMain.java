package serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonMain {

	public static void main(String[] args) {
		Person p = new Person(1990, 10, 15, "Marwin Philips", "secret");
		System.out.println(p);
		try {
			FileOutputStream fileOut = new FileOutputStream(
					"C:/temp/person.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(p);
			out.close();
			fileOut.close();
			System.out
					.println("Serialized data is saved in C:/temp/person.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
		Object o = new Object();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					new File("C:/temp/person.ser")));
			o = ois.readObject();
			System.out.println("Gelesen: " + o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(o.equals(p));
		
		List<Person> pl = new ArrayList<Person>();
		pl.add((Person) o);
		pl.add(p);
		pl.add(p);
		pl.add(p);
		System.out.println(pl.size());
		
		Set<Person> ps = new HashSet<Person>();
		ps.add((Person) o);
		ps.add((Person) o);
		ps.add(p);
		ps.add(p);
		System.out.println(ps.size());
		
	}
}
