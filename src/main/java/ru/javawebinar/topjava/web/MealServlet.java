package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.dao.MealDaoList;
import ru.javawebinar.topjava.dao.MealDaoListImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by d.baskakov on 27.03.2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);

    private static String INSERT_OR_EDIT = "/edit.jsp";
    private static String LIST_MEAL = "/meals.jsp";
//    private MealDaoList dao;
    private MealDaoImpl dao;

    public MealServlet() {
//        this.dao = new MealDaoListImpl();
        this.dao=new MealDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to meals");
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteMeal(id);
            forward = LIST_MEAL;
            request.setAttribute("list", dao.getAllMealsWithExceed());
        }
        if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("id"));
            Meal meal = dao.getMealById(userId);
            request.setAttribute("meal", meal);
        }

        if (action.equalsIgnoreCase("listUser")) {
            request.setAttribute("list", dao.getAllMealsWithExceed());
            forward = LIST_MEAL;
        }


        request.getRequestDispatcher(forward).forward(request, response);
//        response.sendRedirect("meals.jsp");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        int calories = Integer.parseInt(request.getParameter("calories"));
        String description = request.getParameter("description");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        Meal meal = new Meal(id, dateTime, description, calories);

        if (dao.getMealById(id) != null) {
            dao.updateMeal(meal);
        } else {
            dao.addMeal(meal);
        }

//        dao.updateMeal(meal);
        request.setAttribute("list", dao.getAllMealsWithExceed());
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
