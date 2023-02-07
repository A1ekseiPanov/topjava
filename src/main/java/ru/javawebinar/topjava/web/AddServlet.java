package ru.javawebinar.topjava.web;


import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.DAO;
import ru.javawebinar.topjava.dao.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    public DAO<UUID, Meal> dao;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to createMeal");
        req.getRequestDispatcher("/createMeal.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        Meal meal = new Meal(dateTime, description, calories);
        dao.save(meal);
        log.debug("save meal {}", meal);
        resp.sendRedirect(req.getContextPath() + "/meals");
    }
}
