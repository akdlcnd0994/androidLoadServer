package com.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.catalina.connector.Connector;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

public class findInfo {

	final static String DB_URL = "jdbc:oracle:thin:@shortcut_medium?TNS_ADMIN=C:/wallet/Wallet_shortcut";

	final static String DB_USER = "admin";
	final static String DB_PASSWORD = "Rheodml123!!";
	Connection conn = null;
	PreparedStatement pstmt;
	PreparedStatement pstmt2;
	
	private static findInfo instance = new findInfo();
    String returns = "a";
	
	public findInfo(){
		
	}
	
	 public static findInfo getInstance() {
	        return instance;
	 }
	
	public void start() throws SQLException {

		Properties info = new Properties();
		info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
		info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
		info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, 20);

		OracleDataSource ods = new OracleDataSource();
		ods.setURL(DB_URL);
		ods.setConnectionProperties(info);

		// With AutoCloseable, the connection is closed automatically.
		try (OracleConnection connection = (OracleConnection) ods.getConnection()) {
			// Get the JDBC driver name and version
			DatabaseMetaData dbmd = connection.getMetaData();
			System.out.println("Driver Name: " + dbmd.getDriverName());
			System.out.println("Driver Version: " + dbmd.getDriverVersion());
			// Print some connection properties
			System.out.println("Default Row Prefetch Value is: " + connection.getDefaultRowPrefetch());
			System.out.println("Database Username is: " + connection.getUserName());
			System.out.println();
			// Perform a database operation
		}
	}
	
	
	

	/*
	 * Displays first_name and last_name from the employees table.
	 */
	 public String connectionDB(String info, String spn, String ans, String check) {
		 
		 if(check.equals("0")) {
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

	            String sql = "SELECT id FROM ACCOUNT WHERE nickname = ? and ans = ? and spn = ?";
	            pstmt = conn.prepareStatement(sql);	
	            pstmt.setString(1, info);
	            pstmt.setString(2, ans);
	            pstmt.setString(3, spn);

	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                returns = "ID는 " + rs.getString(1)+"입니다.";
	            } else {
	                returns = "존재하지 않는 정보입니다.";
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
	            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
	            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
	        }
	        return returns;
	    }
		 
		 else {
		        try {
		            Class.forName("oracle.jdbc.driver.OracleDriver");
		            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

		            String sql = "SELECT pw FROM ACCOUNT WHERE id = ? and ans = ? and spn = ?";
		            pstmt = conn.prepareStatement(sql);	
		            pstmt.setString(1, info);
		            pstmt.setString(2, ans);
		            pstmt.setString(3, spn);

		            ResultSet rs = pstmt.executeQuery();
		            if (rs.next()) {
		                returns = "PW는 " + rs.getString(1)+"입니다.";
		            } else {
		                returns = "존재하지 않는 정보입니다.";
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
		            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
		            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
		        }
		        return returns;
		 }
	 }
}
