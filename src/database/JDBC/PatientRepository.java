package database.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientRepository extends QueryExecutor {
	public PatientRepository(Connection conn) {
		super(conn);
	}

	public ArrayList<Patient> getAllPatients() throws Exception {
		ResultSet rs = executeSelect("select PatientNb, Name, Firstname, Gender, Birthdate, Street, ZIP, City,Phone, InsuranceNb from patient");
		return mapPatient(rs);
	}

	public ArrayList<Patient> getPatientsByName(String name) throws Exception {
		ResultSet rs = executeSelect(String
				.format("select PatientNb, Name, Firstname, Gender, Birthdate, Street,"
						+ " ZIP, City,Phone, InsuranceNb from patient where name='%s'",
						name));
		return mapPatient(rs);
	}

	private ArrayList<Patient> mapPatient(ResultSet rs) throws SQLException {
		ArrayList<Patient> ret = new ArrayList<Patient>();
		while (rs.next()) {
			ret.add(new Patient(rs.getInt("PatientNb"), rs.getString("Name"),
					rs.getString("Firstname"), rs.getString("Gender"), rs
							.getDate("Birthdate"), rs.getString("Street"), rs
							.getString("ZIP"), rs.getString("City"), rs
							.getString("Phone"), rs.getInt("InsuranceNb")));
		}
		return ret;

	}

}
