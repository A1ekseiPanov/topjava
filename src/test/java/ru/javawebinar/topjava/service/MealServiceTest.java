package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
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
        Meal meal = service.get(USER_MEAL_ID, USER_ID);
        assertMatch(meal, userMeal1);
    }

    @Test
    public void delete() {
        service.delete(USER_MEAL_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_MEAL_ID, USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> filteredMeals = service
                .getBetweenInclusive(LocalDate.of(2023, Month.FEBRUARY, 18), LocalDate.of(2023, Month.FEBRUARY, 19), USER_ID);
        assertMatch(filteredMeals, userMeal4, userMeal3, userMeal2, userMeal1);
    }

    @Test
    public void getAll() {
        List<Meal> mealsUser = service.getAll(USER_ID);
        assertMatch(mealsUser, userMeal9, userMeal8, userMeal7, userMeal6, userMeal5, userMeal4, userMeal3, userMeal2, userMeal1);
    }

    @Test
    public void update() {
        Meal updatedMeal = getUpdated();
        service.update(updatedMeal, USER_ID);
        assertMatch(updatedMeal, getUpdated());
    }

    @Test
    public void create() {
        Meal created = service.create(getNewMeal(), USER_ID);
        Integer id = created.getId();
        Meal newMeal = getNewMeal();
        newMeal.setId(id);
        assertMatch(created, newMeal);
        assertMatch(service.get(id, USER_ID), newMeal);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(ADMIN_MEAL_ID, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(ADMIN_MEAL_ID, USER_ID));
    }

    @Test
    public void updateNotFound() {
        assertThrows(NotFoundException.class, () -> service.update(adminMeal, USER_ID));
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(null, userMeal5.getDateTime(), "груша", 69), USER_ID));
    }
}