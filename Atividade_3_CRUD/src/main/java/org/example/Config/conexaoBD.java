// Util/DBConnection.java
package org.example.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexaoBD {

    private static final String URL = "jdbc:postgresql://localhost/CAC";
    private static final String USER = "postgres";
    private static final String PASSWORD = "190280";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
