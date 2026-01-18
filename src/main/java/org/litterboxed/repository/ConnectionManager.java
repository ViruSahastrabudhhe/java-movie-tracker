package org.litterboxed.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection getConn() {
        Connection conn;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/litterbox", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}
