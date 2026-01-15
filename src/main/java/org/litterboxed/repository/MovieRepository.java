package org.litterboxed.repository;

import org.litterboxed.domain.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository implements Repository<Movie> {

    @Override
    public void store(Movie movie) {
        try (Connection conn = ConnectionManager.getConn()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO movies (title) VALUES (?)");
            stmt.setString(1, movie.getTitle());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try (Connection conn = ConnectionManager.getConn()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM movies WHERE id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie findById(long id) {
        Movie movie = new Movie();

        try (Connection conn = ConnectionManager.getConn()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM movies WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                movie.setTitle(rs.getString("title"));
            }

            return movie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConn()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM movies");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setTitle(rs.getString("title"));

                movies.add(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movies;
    }
}
