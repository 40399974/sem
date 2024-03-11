package com.napier.sem;

import java.sql.*;

public class App
{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/world";
        String username = "root";
        String password = "root_password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database!");

            // Example query: Retrieve names and populations of cities
            String query = "SELECT name, population FROM country ORDER BY population DESC";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                // Process the result set
                while (resultSet.next()) {
                    String cityName = resultSet.getString("Name");
                    int population = resultSet.getInt("Population");

                    System.out.println("City: " + cityName + ", Population: " + population);
                }
            }

            // Now you can execute SQL queries
            // Example: SELECT * FROM city
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}