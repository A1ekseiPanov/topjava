package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Collection<MealTo> getAll() {
        log.info("getAll");
        return MealsUtil.getTos(service.getAll(authUserId()), SecurityUtil.authUserCaloriesPerDay());
    }

    public Collection<MealTo> getAllFilteredByTime(LocalTime startTime, LocalTime finishTime) {
        log.info("getAllFilteredByTime");
        return MealsUtil.getFilteredByTime(service.getAll(authUserId()), SecurityUtil.authUserCaloriesPerDay(), startTime, finishTime);
    }

    public Collection<MealTo> getAllFilteredByDate(LocalDate startDate, LocalDate finishDate) {
        log.info("getAllFilteredByTime");
        return MealsUtil.getFilteredByDate(service.getAll(authUserId()), SecurityUtil.authUserCaloriesPerDay(), startDate, finishDate);
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, authUserId());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, authUserId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, authUserId());
    }

    public void update(Meal meal, int id) {
        log.info("update {}", meal);
        assureIdConsistent(meal, id);
        service.update(meal, authUserId());
    }
}