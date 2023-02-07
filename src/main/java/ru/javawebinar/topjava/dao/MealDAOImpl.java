package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;


public class MealDAOImpl implements DAO<UUID, Meal> {
    private final List<Meal> meals = new CopyOnWriteArrayList<>();

    {
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public List<Meal> findAll() {
        return meals;
    }

    @Override
    public Meal findById(UUID id) {
        return meals.stream().filter(m-> m.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        return meals.remove(findById(id));
    }

    @Override
    public void update(UUID id, Meal entity) {
        Meal meal = findById(id);
        meal.setDateTime(entity.getDateTime());
        meal.setDescription(entity.getDescription());
        meal.setCalories(entity.getCalories());

    }

    @Override
    public void save(Meal entity) {
        meals.add(entity);
    }
}

