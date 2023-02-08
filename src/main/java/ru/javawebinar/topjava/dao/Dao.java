package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface Dao<K, T> {

    Collection<Meal> findAll();

    T findById(K id);

    void delete(K id);

    Meal save(T entity);

}
