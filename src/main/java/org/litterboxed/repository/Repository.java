package org.litterboxed.repository;

import org.litterboxed.domain.model.Movie;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T, K> {
    void save(T data);
    void delete(K id);
    List<T> findAll();
    T findById(K id);
    T findByName(String name);
    boolean existsById(K id);
    int count();
}