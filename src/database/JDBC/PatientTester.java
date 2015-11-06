package database.JDBC;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;


public class PatientTester {
	private static String DB_FILE = "./jars/hsqldb/lib";
	@Test
	public void TestPatient() throws Exception{
		//Class.forName("net.sourceforge.jtds.jdbc");
		String urlMssql = "jdbc:jtds:sqlserver://corpus.bfh.ch:55783;Database=X3r2015;user=philm2;password=philm2";
		Connection connMssql = DriverManager.getConnection(urlMssql, "", "");
		
		String urlHsqldb = "jdbc:hsqldb:file:" + DB_FILE + ";shutdown=true";
		Connection connHsqldb = DriverManager.getConnection(urlHsqldb,"SA","");
		PatientRepository pr = new PatientRepository(connMssql);
		ArrayList<Patient> allPatients = pr.getAllPatients();
		assertFalse(allPatients.isEmpty());
		ArrayList<Patient> namedPatients = pr.getPatientsByName("Wenk");
		for(Patient p :namedPatients){
			if(!p.getName().equals("Wenk")){
				assertTrue(false);
			}
		}
		connMssql.close();
	}
}
