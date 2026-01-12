package org.litterboxed.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Repository {

    private final String dbName;

    public Repository(String dbName) {
        this.dbName = dbName;
    }

    public Connection getConn() throws SQLException {
        Connection conn = DriverManager.getConnection(this.dbName, "root", "");

        try (conn) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn.prepareStatement("CREATE TABLE movies (id int auto_increment primary key, name varchar(255)").executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}
