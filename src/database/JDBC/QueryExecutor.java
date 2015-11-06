package database.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryExecutor {
	Connection conn;
	ResultSet rs;
	public QueryExecutor(Connection conn) {
		this.conn = conn;
	}
	public ResultSet executeSelect(String sqlquery) throws Exception {
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlquery);
			return rs;

		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			throw e;
		}		
	}
}
