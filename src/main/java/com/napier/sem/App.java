package com.napier.sem;

import java.sql.*;

public class App
{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/world";
        String username = "root";
        String password = "root_password";

        String query = "Select name, population from country;";

        query(url, username, password, query, "Name", "Population");
    }

    public static void query(String url, String username, String password, String query, String... columns) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database!");

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                // Process the result set
                while (resultSet.next()) {
                    for(String column : columns) {
                        System.out.print(column + ": " + resultSet.getString(column) + ", ");
                    }
                    System.out.println();
                }
            }

            // Now you can execute SQL queries
            // Example: SELECT * FROM city
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}