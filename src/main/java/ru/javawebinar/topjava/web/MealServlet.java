package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.MealDaoInMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    public Dao<Integer, Meal> dao;
    private static final String MEAL_LIST = "/meals.jsp";
    private static final String CREATE_OR_UPDATE = "/createOrUpdate.jsp";
    private final int CALORIES_PER_DAY = 2000;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new MealDaoInMemory();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            dao.delete(id);
            log.debug("delete meal by id {}", id);
            forward = MEAL_LIST;
            request.setAttribute("meals", MealsUtil.getMealsTo(dao.findAll(), CALORIES_PER_DAY));
        } else if (action.equalsIgnoreCase("update")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Meal meal = dao.findById(id);
            log.debug("redirect to update meal {} by id {}", meal, id);
            request.setAttribute("m", meal);
            forward = CREATE_OR_UPDATE;
        } else if (action.equalsIgnoreCase("listMeal")) {
            forward = MEAL_LIST;
            log.debug("redirect to meals");
            request.setAttribute("meals", MealsUtil.getMealsTo(dao.findAll(), CALORIES_PER_DAY));
        } else {
            forward = CREATE_OR_UPDATE;
            log.debug("redirect to create meal");
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        Meal meal = new Meal(dateTime, description, calories);
        if (id == null || id.isEmpty()) {
            dao.save(meal);
            log.debug("create new meal {}", meal);
        } else {
            meal.setId(Integer.parseInt(id));
            dao.save(meal);
            log.debug("update meal {} by id {}", meal, id);
        }
        RequestDispatcher view = request.getRequestDispatcher(MEAL_LIST);
        request.setAttribute("meals", MealsUtil.getMealsTo(dao.findAll(), CALORIES_PER_DAY));
        view.forward(request, response);
    }
}
