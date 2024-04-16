package com.napier.sem;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TopNoCountriesIntegrationTest {

    @Test
    void testQuery() {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/world";
        String username = "root";
        String password = "root_password";

        // Define SQL query to retrieve data
        String query = "SELECT COUNT(*) FROM country";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             java.sql.Statement statement = connection.createStatement()) {

            // Execute the query
            java.sql.ResultSet resultSet = statement.executeQuery(query);

            // Process the result
            int rowCount = 0;
            if (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }

            // Verify that the query returns some result
            assertTrue(rowCount > 0, "Query should return some result");
        } catch (SQLException e) {
            fail("SQL Exception occurred: " + e.getMessage());
        }
    }
}
