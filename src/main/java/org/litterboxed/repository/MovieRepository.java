package org.litterboxed.repository;

import org.litterboxed.domain.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository implements Repository<Movie, Long> {

    @Override
    public void save(Movie movie) {
        Movie existingMovie = findById(movie.getId());

        if (existingMovie != null) {
            try (Connection conn = ConnectionManager.getConn()) {
                PreparedStatement stmt = conn.prepareStatement("UPDATE movies SET title = ? WHERE id = ?");
                stmt.setString(1, movie.getTitle());
                stmt.setLong(2, existingMovie.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return;
        }

        try (Connection conn = ConnectionManager.getConn()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO movies (title) VALUES (?)");
            stmt.setString(1, movie.getTitle());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection conn = ConnectionManager.getConn()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM movies WHERE id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie findByName(String name) {
        Movie movie = new Movie();

        try (Connection conn = ConnectionManager.getConn()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM movies WHERE title = ?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                movie.setTitle(rs.getString("title"));
                movie.setId(rs.getLong("id"));
                return movie;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie findById(Long id) {
        Movie movie = new Movie();

        try (Connection conn = ConnectionManager.getConn()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM movies WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                movie.setTitle(rs.getString("title"));
                movie.setId(rs.getLong("id"));
                return movie;
            } else {
                return null;
            }
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

    @Override
    public boolean existsById(Long id) {
        Movie movie = findById(id);
        return movie != null;
    }

    @Override
    public int count() {
        int count = 0;

        try (Connection conn = ConnectionManager.getConn()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM movies");
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }
}