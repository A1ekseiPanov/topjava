package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.DAO;
import ru.javawebinar.topjava.dao.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet("/meals")
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    public DAO<UUID, Meal> dao;
    private final int CALORIES_PER_DAY = 2000;

    @Override
    public void init() throws ServletException {
        final Object o = getServletContext().getAttribute("daoInit");
        if (o == null || !(o instanceof MealDAOImpl)) {
            throw new IllegalStateException("You're repo does not initialize!");
        } else {
            this.dao = (DAO<UUID, Meal>) o;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MealTo> meals = MealsUtil.getMealsTo(dao.findAll(),CALORIES_PER_DAY);
        log.debug("redirect to meals");
        request.setAttribute("meals", meals);
//        response.sendRedirect(request.getContextPath() + "/meals.jsp");
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
