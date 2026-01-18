package org.litterboxed.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.litterboxed.domain.MoviesLoader;
import org.litterboxed.domain.model.Movie;

import java.util.ArrayList;
import java.util.List;
import static org.litterboxed.domain.MoviesLoader.MOVIES_INIT_DATA;

import static org.junit.jupiter.api.Assertions.*;

class MovieRepositoryTest {
    private MovieRepository movieRepository = new MovieRepository();
    private MoviesLoader moviesLoader = new MoviesLoader();

    private List<Movie> expectedMovies = new ArrayList<>();

    @BeforeEach
    public void setup() {
        TablesManager.createTable();
        initExpectedMoviesList();
        moviesLoader.loadMovies();
        ConnectionManager.getConn();
    }

    @Test
    void testStore() {
        Movie movie = new Movie();
        movie.setTitle("MOVIE 1");

        movieRepository.save(movie);

        assertNotNull(movieRepository.findAll());
        assertEquals(expectedMovies.get(0).getTitle(), movieRepository.findAll().get(0).getTitle());
    }

    @Test
    void testStoreWithExistingRow() {
        Movie movie = new Movie();
        movie.setTitle("MOVIE 1");
        movie.setId(1);

        Movie dupeMovie = new Movie();
        dupeMovie.setTitle("MOVIE 2");
        dupeMovie.setId(movie.getId());

        movieRepository.save(movie);
        movieRepository.save(dupeMovie);

        assertEquals(dupeMovie.getTitle(), movieRepository.findAll().get(0).getTitle());
        assertEquals(dupeMovie.getId(), movieRepository.findAll().get(0).getId());
    }

    @Test
    void testDelete() {
        movieRepository.delete(1L);
        expectedMovies.remove(0);

        assertNotNull(movieRepository.findAll());
        assertEquals(expectedMovies.size(), movieRepository.findAll().size());

        for (int i = 0; i < movieRepository.findAll().size(); i++) {
            assertEquals(expectedMovies.get(i).getTitle(), movieRepository.findAll().get(i).getTitle());
        }
    }

    @Test
    void testCount() {
        int testCount = movieRepository.count();
        assertEquals(expectedMovies.size(), testCount);
    }

    @Test
    void testExistsById() {
        Long id = 1L;
        assertTrue(movieRepository.existsById(id));
    }

    @Test
    void testFindByName() {
        Movie movie = new Movie();
        movie.setTitle("MOVIE 1");

        movieRepository.findByName(movie.getTitle());

        assertNotNull(movieRepository.findAll());
        assertEquals(movie.getTitle(), movieRepository.findAll().get(0).getTitle());
    }

    @Test
    void testFindAll() {
        assertNotNull(movieRepository.findAll());
        assertEquals(expectedMovies.size(), movieRepository.findAll().size());

        for (int i = 0; i < movieRepository.findAll().size(); i++) {
            assertEquals(expectedMovies.get(i).getTitle(), movieRepository.findAll().get(i).getTitle());
        }
    }

    @Test
    void testFindById() {
        assertNotNull(movieRepository.findById(9L));
        assertEquals(expectedMovies.get(8).getTitle(), movieRepository.findById(9L).getTitle());
    }

    @AfterEach
    public void tearDown() {
        TablesManager.dropTable();
    }

    private void initExpectedMoviesList() {
        for (int i = 0; i < MOVIES_INIT_DATA.length; i++) {
            String movieInitTitle =  MOVIES_INIT_DATA[i];
            Movie movie = new Movie();
            movie.setId(i);
            movie.setTitle(movieInitTitle);

            expectedMovies.add(movie);
        }
    }
}