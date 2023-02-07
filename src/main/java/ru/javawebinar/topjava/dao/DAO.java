package ru.javawebinar.topjava.dao;

import java.util.List;
import java.util.UUID;

public interface DAO<K, T> {

    List<T> findAll();

    T findById(K id);

    boolean delete(K id);

    void update(K id, T entity);

    void save(T entity);

}
