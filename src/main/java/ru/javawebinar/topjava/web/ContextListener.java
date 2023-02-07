package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.DAO;
import ru.javawebinar.topjava.dao.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.UUID;

@WebListener
public class ContextListener implements ServletContextListener {
    private DAO<UUID, Meal> dao;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final ServletContext servletContext = sce.getServletContext();
        dao = new MealDAOImpl();
        servletContext.setAttribute("daoInit", dao);
    }
}
