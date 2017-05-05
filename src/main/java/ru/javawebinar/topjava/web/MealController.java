package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Created by d.baskakov on 05.05.2017.
 */
@Controller
@RequestMapping(value = "/meals")
public class MealController {

    @Autowired
    MealRestController mealRestController;

    //todo пока засунуть все в один метод из сервлета

   @GetMapping
    public String meals(HttpServletRequest request,Model model)
    {
        System.out.println(request.getParameter("id"));
        String action=request.getParameter("id");
        if (action!=null)
        {
            int id=getId(request);
            mealRestController.delete(id);
        }


        model.addAttribute("meals", mealRestController.getAll());
        return "meals";
    }

    @RequestMapping(value ="?action=delete&id={id}",method = RequestMethod.GET)
    public String delMeals(@PathVariable("id")int id)
    {
        mealRestController.delete(id);
        return "redirect:/meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
