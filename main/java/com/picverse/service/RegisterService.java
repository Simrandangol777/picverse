package com.picverse.service;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.picverse.config.DatabaseConfig;
import com.picverse.model.UserModel;

/**
 * RegisterService handles the registration of new user. It manages database
 * interactions for user registration.
 */
public class RegisterService {

	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public RegisterService() {
		try {
			this.dbConn = DatabaseConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Registers a new User in the database.
	 *
	 * @param userModel the user details to be registered
	 * @return Boolean indicating the success of the operation
	 */
	public Boolean addUser(UserModel userModel) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		// Prepare the SQL statement for inserting a new user
		String insertQuery = "INSERT INTO user (name, username, email, password) VALUES (?, ?, ?, ?)";

		
		
		try {
			// Prepare the SQL statement
			PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery);
			insertStmt.setString(1, userModel.getName());
			insertStmt.setString(2, userModel.getUsername());
			insertStmt.setString(3, userModel.getEmail());
			insertStmt.setString(4, userModel.getPassword());
			return insertStmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error during user registration: " + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}
}
