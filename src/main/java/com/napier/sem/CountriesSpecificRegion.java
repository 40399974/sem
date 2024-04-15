package com.napier.sem;

import java.sql.*;
public class CountriesSpecificRegion {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/world";
        String username = "root";
        String password = "root_password";

        // Define the desired region
        String targetRegion = "Western Europe";

        // Define SQL query to retrieve data for the specific region
        String query = "SELECT name, population FROM country WHERE region = ?";

        // Define order for the report
        String orderBy = "population DESC";

        // Call the query method to execute the SQL query
        query(url, username, password, query, orderBy, "Name", "Population", targetRegion);
    }

    // Method to execute SQL query and display results
    public static void query(String url, String username, String password, String query, String orderBy, String... columns) {
        // Append ORDER BY clause to the SQL query
        query = query + " ORDER BY " + orderBy;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Connection successful
            System.out.println("Connected to the database!");

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Set the value of the region parameter in the prepared statement
                statement.setString(1, columns[2]); // Assuming the region is passed as the third argument

                try (ResultSet resultSet = statement.executeQuery()) {
                    // Process the result set
                    while (resultSet.next()) {
                        // Print each column value for the current row
                        for (int i = 0; i < columns.length - 1; i++) {
                            System.out.print(columns[i] + ": " + resultSet.getString(i + 1) + ", ");
                        }
                        System.out.println(); // Move to the next line after printing all columns
                    }
                }
            }

            // You can add more SQL queries here

        } catch (SQLException e) {
            // Exception occurred during database operation, print the stack trace
            e.printStackTrace();
        }
    }
}
