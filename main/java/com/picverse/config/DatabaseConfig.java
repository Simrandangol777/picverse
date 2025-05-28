package com.picverse.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DbConfig is a configuration class for managing database connections. It
 * handles the connection to a MySQL database using JDBC.
 */
public class DatabaseConfig {
  // Database configuration information
  private static final String DB_NAME = "picverse";
  private static final String URL =
    "jdbc:mysql://localhost:3306/" + DB_NAME;
  private static final String USERNAME = "root";
  private static final String PASSWORD = "";

  /**
   * Establishes a connection to the database.
   *
   * @return Connection object for the database
   * @throws SQLException           if a database access error occurs
   * @throws ClassNotFoundException if the JDBC driver class is not found
   */
  public static Connection getDbConnection() throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    // Establish the connection
    Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

    // Check if the connection was successful and print a message
    if (connection != null) {
      System.out.println("Database connection successful!");
    } else {
      System.out.println("Failed to connect to the database.");
    }

    return connection;
  }

  public static void main(String[] args) {
    try {
      // Test the connection
      getDbConnection();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
