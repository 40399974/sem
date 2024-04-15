package com.napier.sem;

import java.sql.*;

public class TopNoRegions {

    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/world";
        String username = "root";
        String password = "root_password";

        // Define the desired region
        String targetRegion = "Central America";

        // Define SQL query to retrieve data
        String query = "SELECT name, population, region FROM country WHERE region = ?";

        // Define order for the report
        String orderBy = "population DESC";

        // Number of top items to return
        int topItems = 10;

        // Call the query method to execute the SQL query
        query(url, username, password, query, orderBy, topItems, "Name", "Population", "Region", targetRegion);
    }

    // Method to execute SQL query and display results
    public static void query(String url, String username, String password, String query, String orderBy, int topItems, String... columns) {
        // Append ORDER BY and LIMIT clauses to the SQL query
        query = query + " ORDER BY " + orderBy + " LIMIT " + topItems;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Connection successful
            System.out.println("Connected to the database!");

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Set the value of the region parameter in the prepared statement
                statement.setString(1, columns[3]); // Assuming the region is passed as the fourth argument

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
