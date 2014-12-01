package systemIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

public class Gzip {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		InputStream is = System.in;
		Scanner s = new Scanner(is);

		CharArrayWriter caw = new CharArrayWriter();
		Writer w = new BufferedWriter(caw);
		PrintWriter pw = new PrintWriter(w);
		while (s.hasNext()) {
			pw.println(s.nextLine());
		}
		pw.flush();
		pw.close();
		char[] charArray = caw.toCharArray();

		s = new Scanner(new CharArrayReader(charArray));
		pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(
				"c:/temp/Test.gz"))));
		while (s.hasNext()) {
			pw.println(s.next());
		}
		pw.flush();
		pw.close();

		Scanner sc = new Scanner(new BufferedReader(new FileReader(new File(
				"c:/temp/Test.gz"))));
		while (sc.hasNext()) {
			System.out.println(sc.nextLine());
		}
		sc.close();
	}
}
