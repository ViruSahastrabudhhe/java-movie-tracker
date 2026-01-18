package org.litterboxed.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TablesManager {
    public static void createTable() {
        try (Connection conn = ConnectionManager.getConn()) {
            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS movies (id int auto_increment primary key, title varchar(255));");
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dropTable() {
        try (Connection conn = ConnectionManager.getConn()) {
            PreparedStatement stmt = conn.prepareStatement("DROP TABLE IF EXISTS movies");
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
