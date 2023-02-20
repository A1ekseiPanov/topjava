package ru.javawebinar.topjava.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(1, USER_ID);
        Assertions.assertThat(meal).usingRecursiveComparison().ignoringFields("id").isEqualTo(MealTestData.mealUser);
    }

    @Test
    public void delete() {
        service.delete(1, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(1, USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> filteredMeals = service
                .getBetweenInclusive(LocalDate.of(2023, Month.FEBRUARY, 20), LocalDate.of(2023, Month.FEBRUARY, 21), USER_ID);
        assertThat(filteredMeals).hasSize(2);
    }

    @Test
    public void getAll() {
        List<Meal> mealsUser = service.getAll(USER_ID);
        Collections.reverse(mealsUser);
        assertThat(mealsUser).usingRecursiveComparison().ignoringFields("id")
                .isEqualTo(MealTestData.mealsUser);
    }

    @Test
    public void update() {
        Meal updatedMeal = service.get(1, USER_ID);
        updatedMeal.setId(1);
        updatedMeal.setDateTime(LocalDateTime.of(2022, Month.FEBRUARY, 11, 12, 23));
        updatedMeal.setDescription("вафля");
        updatedMeal.setCalories(200);
        service.update(updatedMeal, USER_ID);
        assertThat(service.get(1, USER_ID)).isEqualTo(updatedMeal);
    }

    @Test
    public void create() {
        Meal created = service.create(MealTestData.newMeal, USER_ID);
        Integer id = created.getId();
        Meal newMeal = MealTestData.newMeal;
        newMeal.setId(id);
        Assertions.assertThat(created).isEqualTo(newMeal);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(6, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(6, USER_ID));
    }

    @Test
    public void updateNotFound() {
        assertThrows(NotFoundException.class, () -> service.update(service.get(6, ADMIN_ID), USER_ID));
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(null, LocalDateTime.of(2023, Month.FEBRUARY, 19, 18, 39), "груша", 69), USER_ID));
    }

}