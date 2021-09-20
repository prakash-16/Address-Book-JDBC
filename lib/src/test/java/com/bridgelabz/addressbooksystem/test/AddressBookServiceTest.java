package com.bridgelabz.addressbooksystem.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import com.bridgelabz.addressbooksystem.main.AddressBookService;

public class AddressBookServiceTest {

	public static String jdbcURL = "jdbc:mysql://localhost:3306/address_book_service?useSSL=false";
	public static String userName = "root";
	public static String password = "mysql123@";
	public static String query = "Select * from address_book_table;";
	AddressBookService service = new AddressBookService();

	@Test
	public void checkConnectionWithDBisEastablished() throws SQLException {
		try {
			boolean result = service.checkConnection(jdbcURL, userName, password);
			Assert.assertEquals(true, result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void retriveDataFromTable_OrElseCreateATable() throws SQLException {
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, userName, password);
			String result = service.getDataFromTables(connection, "address_book_table", query);
			System.out.println(result);
			Assert.assertEquals("Data is printed", result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateDetailsInDatabase_checkIfSynced() throws SQLException {
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, userName, password);
			service.updateCityThroughFirstName(connection, "Sarvesh", "Mumbai");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
