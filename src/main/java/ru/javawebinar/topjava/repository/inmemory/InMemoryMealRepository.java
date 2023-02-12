package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 3));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            log.info("create {}", meal);
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        if (checkUserId(meal.getId(), userId)) {
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        } else {
            return null;
        }

    }

    @Override
    public boolean delete(int id, int userId) {
        if (checkUserId(id, userId)) {
            log.info("delete mealId:{} by userId{}", id, userId);
            return repository.remove(id) != null;
        }
        log.error("user with id {} is trying to delete meal {}", userId, id);
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        if (checkUserId(id, userId)) {
            log.info("get meal {} by user id {}", id, userId);
            return repository.get(id);
        }
        log.error("user with id {} is trying to get meal {}", userId, id);
        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        log.info("get all by userId {}", userId);
        return repository.values()
                .stream()
                .filter(meal -> meal.getUserId().equals(userId))
                .sorted(((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime())))
                .collect(Collectors.toList());
    }

    private boolean checkUserId(int id, int userId) {
        return repository.get(id).getUserId() == userId;

    }
}

