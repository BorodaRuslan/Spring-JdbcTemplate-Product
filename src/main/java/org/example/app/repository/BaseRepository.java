package org.example.app.repository;

import org.example.app.entity.Product;

import java.util.List;
import java.util.Optional;

public interface BaseRepository <T>{
    boolean create(T object);
    Optional<List<T>> getAll();
    Optional<T> getById(Long id);
    boolean update(T object);
    boolean delete(T object);


}
