package ch.bfh.philm2.helpers;

import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class H.
 */
public class H {
	
	/** The s. */
	public Scanner s;
	/**
	 * P l.
	 *
	 * @param printMe the print me
	 */
	public static void pL(String printMe){
		System.out.println(printMe);
	}
	/**
	 * Opens the System.In Scanner of the Helper-Class
	 */
	public void openScanner(){
		s = new Scanner(System.in);
	}
	
	/**
	 * ReadLine with String before the ReadLine written on the Console.
	 *
	 * @param question the text written before the readline.
	 * @return the read string
	 */
	public String rL(String question){
		pL(question);
		return s.nextLine();
	}
	
	/**
	 * Close scanner.
	 */
	public void closeScanner(){
		s.close();
	}
}
