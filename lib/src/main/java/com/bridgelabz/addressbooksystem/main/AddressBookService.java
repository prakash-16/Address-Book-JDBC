package com.bridgelabz.addressbooksystem.main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddressBookService {

	public boolean checkConnection(String URL, String userName, String password) throws SQLException {

		try {
			Connection connection = DriverManager.getConnection(URL, userName, password);
			System.out.println("Connection eastablished with data base.");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getDataFromTables(Connection connection, String tableName, String query) throws SQLException {
		try {
			DatabaseMetaData db = connection.getMetaData();
			ResultSet tables = db.getTables(null, null, "address_book_table", null);
			Statement stm = connection.createStatement();
			while (tables.next()) {
				String table = tables.getString("TABLE_NAME");
				if (table.equals(tableName)) {
					ResultSet resultSet = stm.executeQuery(query);
					while (resultSet.next()) {
						System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " "
								+ resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5)
								+ " " + resultSet.getString(6) + " " + resultSet.getInt(7) + " " + resultSet.getInt(8)
								+ " " + resultSet.getString(9));
					}
					return "Data is printed";
				}
				continue;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Table is not present in data base";
	}

	public void updateCityThroughFirstName(Connection connection, String FirstName, String city) throws SQLException {
		try {
			PreparedStatement stm = connection
					.prepareStatement("update address_book_table set City = ? where First_name = ?;");
			stm.setString(1, city);
			stm.setString(2, FirstName);
			stm.executeUpdate();
			System.out.println("Details has been updated successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
