package ch.bfh.btx8052.test_hs14_final.model.implementation;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Scanner;

import ch.bfh.btx8052.test_hs14_final.model.definition.BloodPressureSample;
import ch.bfh.btx8052.test_hs14_final.model.definition.PersistingHaemodynAnometer;

public class PersistingHaemodynAnometerModel extends HaemodynAnometerModel implements Serializable, PersistingHaemodynAnometer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public void persistLatestBloodPressureSample(OutputStream outputStream) {
		String stringToAdd = getLatestBloodPressureSample().toString();
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(outputStream);
			out.writeObject(getLatestBloodPressureSample());
			//outputStream.write(stringToAdd.getBytes(Charset.forName("UTF-8")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


	// "InternalBloodPressureSample{" + "systolicInMMHg=" + systolicInMMHg +
	// ", diastolicInMMHg=" + diastolicInMMHg + ", pulse=" + pulse +
	// ", timestampInMillis="
	// + timestampInMillis + '}';
	@Override
	public BloodPressureSample retrieveBloodPressureSample(
			InputStream inputStream) {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(inputStream);
			return (BloodPressureSample)ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
//		Scanner scanner = new Scanner(inputStream);
//		String nextLine = scanner.next();
//		int systolicInMMHg, diastolicInMMHg, pulse;
//		long timeStampInMillis;
//		String textLength = "InternalBloodPressureSample{systolicInMMHg=";
//		systolicInMMHg = Integer.parseInt(nextLine.substring(
//				textLength.length(), nextLine.length() - 1));
//		nextLine = scanner.next();
//		textLength = "diastolicInMMHg=";
//		diastolicInMMHg = Integer.parseInt(nextLine.substring(
//				textLength.length(), nextLine.length() - 1));
//		nextLine = scanner.next();
//		textLength = "pulse=";
//		pulse = Integer.parseInt(nextLine.substring(textLength.length(),
//				nextLine.length() - 1));
//		nextLine = scanner.next();
//		textLength = "timestampInMillis=";
//		timeStampInMillis = Long.parseLong(nextLine.substring(
//				textLength.length(), nextLine.length() - 1));
//		return new BloodPressureSampleDTO(systolicInMMHg, diastolicInMMHg,
//				pulse, timeStampInMillis);
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
