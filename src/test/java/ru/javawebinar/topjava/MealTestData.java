package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealTestData {
    public static final List<Meal> mealsUser = new ArrayList<>(Arrays.asList(
            new Meal(LocalDateTime.of(2023, Month.FEBRUARY, 19, 18, 39), "хот дог", 1200),
            new Meal(LocalDateTime.of(2023, Month.FEBRUARY, 20, 18, 40), "чизбургер", 1500),
            new Meal(LocalDateTime.of(2023, Month.FEBRUARY, 21, 18, 41), "пицца", 1600)));

    public static final Meal newMeal = new Meal(null, LocalDateTime.of(2022, Month.FEBRUARY, 12, 12, 29), "пельмени", 400);

    public static final Meal mealUser = new Meal(LocalDateTime.of(2023, Month.FEBRUARY, 19, 18, 39), "хот дог", 1200);
}


