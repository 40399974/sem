package com.napier.sem;

import java.sql.*;

public class SelectSpecific {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/world";
        String username = "root";
        String password = "root_password";

        // Define criteria for the query
        String continent = "Africa"; // Specify the continent here
        String country = ""; // Specify country
        String region = "Eastern Africa"; // Specify region
        String city = ""; // Specify city

        // Define SQL query to retrieve data for countries based on specified criteria
        String query = "SELECT country.name AS Country, city.name AS City, counrty.region AS Region " +
                "FROM country " +
                "JOIN city ON country.code = city.countrycode" +
                "WHERE 1 = 1";

        // If continent is specified, add it to the query
        if (!continent.isEmpty()) {
            query += " AND country.continent = '" + continent + "'";
        }

        // If country is specified, add it to the query
        if (!country.isEmpty()) {
            query += " AND country.name = '" + country + "'";
        }

        // If region is specified, add it to the query
        if (!region.isEmpty()) {
            query += " AND city.district = '" + region + "'";
        }

        // If city is specified, add it to the query
        if (!city.isEmpty()) {
            query += " AND city.name = '" + city + "'";
        }

        // Define order for the report
        String orderBy = "country.name, city.name";

        // Call the query method to execute the SQL query
        query(url, username, password, query, orderBy, "Country", "City", "Region");
    }

    // Method to execute SQL query and display results
    public static void query(String url, String username, String password, String query, String orderBy, String... columns) {
        // Append ORDER BY clause to the SQL query
        query = query + " ORDER BY " + orderBy;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Connection successful
            System.out.println("Connected to the database!");

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                // Process the result set
                while (resultSet.next()) {
                    // Print each column value for the current row
                    for (String column : columns) {
                        System.out.print(column + ": " + resultSet.getString(column) + ", ");
                    }
                    System.out.println(); // Move to the next line after printing all columns
                }
            }

        } catch (SQLException e) {
            // Exception occurred during database operation, print the stack trace
            e.printStackTrace();
        }
    }
}
