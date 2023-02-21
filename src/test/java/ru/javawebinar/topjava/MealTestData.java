package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_MEAL_ID = START_SEQ;
    public static final int ADMIN_MEAL_ID = USER_MEAL_ID + 8;


    public static final Meal mealUser1 = new Meal(USER_MEAL_ID, LocalDateTime.of(2023, Month.FEBRUARY, 19, 9, 39), "завтрак", 200);
    public static final Meal mealUser2 = new Meal(USER_MEAL_ID + 1, LocalDateTime.of(2023, Month.FEBRUARY, 19, 14, 40), "обед", 700);
    public static final Meal mealUser3 = new Meal(USER_MEAL_ID + 2, LocalDateTime.of(2023, Month.FEBRUARY, 19, 18, 41), "ужин", 1000);
    public static final Meal mealUser4 = new Meal(USER_MEAL_ID + 3, LocalDateTime.of(2023, Month.FEBRUARY, 19, 22, 19), "полдник", 100);
    public static final Meal mealUser5 = new Meal(USER_MEAL_ID + 4, LocalDateTime.of(2023, Month.FEBRUARY, 20, 10, 3), "завтрак", 500);
    public static final Meal mealUser6 = new Meal(USER_MEAL_ID + 5, LocalDateTime.of(2023, Month.FEBRUARY, 20, 13, 52), "обед", 1200);
    public static final Meal mealUser7 = new Meal(USER_MEAL_ID + 6, LocalDateTime.of(2023, Month.FEBRUARY, 20, 18, 39), "ужин", 600);
    public static final Meal mealUser8 = new Meal(USER_MEAL_ID + 7, LocalDateTime.of(2023, Month.FEBRUARY, 20, 22, 40), "перекус", 700);
    public static final Meal mealAdmin = new Meal(ADMIN_MEAL_ID, LocalDateTime.of(2023, Month.FEBRUARY, 19, 9, 39), "завтрак", 600);

    public static Meal getNewMeal() {
        return new Meal(null, LocalDateTime.of(2022, Month.FEBRUARY, 12, 12, 29), "пельмени", 400);
    }

    public static final Meal mealUser = new Meal(USER_MEAL_ID, LocalDateTime.of(2023, Month.FEBRUARY, 19, 9, 39), "завтрак", 200);

    public static Meal getUpdated() {
        Meal updated = new Meal(USER_MEAL_ID, LocalDateTime.of(2023, Month.FEBRUARY, 19, 9, 39), "завтрак", 200);
        updated.setDateTime(LocalDateTime.of(2022, Month.FEBRUARY, 11, 12, 23));
        updated.setDescription("вафля");
        updated.setCalories(200);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}


