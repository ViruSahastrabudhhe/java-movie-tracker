package org.litterboxed.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.litterboxed.repository.ConnectionManager;

public class MoviesLoader {
    private static final String LOAD_MOVIES_SQL =
            "INSERT INTO movies (title) VALUE ";

    public static final String[] MOVIES_INIT_DATA =
            { "MOVIE 1", "MOVIE 2", "MOVIE 3", "MOVIE 4", "MOVIE 5",
            "MOVIE 6", "MOVIE 7", "MOVIE 8", "MOVIE 9", "MOVIE 10" };

    public void loadMovies() {
        for (String title : MOVIES_INIT_DATA) {
            String sql = LOAD_MOVIES_SQL + "('" + title + "');";

            try (Connection conn = ConnectionManager.getConn()) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
