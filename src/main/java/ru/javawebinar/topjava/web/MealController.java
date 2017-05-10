package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Created by d.baskakov on 05.05.2017.
 */
@Controller
@RequestMapping(value = "/meals")
public class MealController {

    @Autowired
    MealRestController mealRestController;

   @GetMapping
    public String meals(Model model)
    {
        model.addAttribute("meals", mealRestController.getAll());
        return "meals";
    }

    @GetMapping(value = "/delete")
    public String delMeals(@RequestParam("id")int id)
    {
        mealRestController.delete(id);
        return "redirect:/meals";
    }

    //todo  create - нажали кнопку в meals
    @GetMapping(value = "/create")
    public String createMeal(Model model)
    {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal",meal);
        return "meal";
    }


    //todo  update -  тоже самое что и create + id
    @GetMapping(value = "/update")
    public String updateMeal(Model model,HttpServletRequest request)
    {
        Meal meal=mealRestController.get(getId(request));
        model.addAttribute("meal",meal);
//        request.setAttribute("meal", meal);
        return "meal";
    }

    //todo save - нажали кнопку в meal
    // те значения что есть в полях сохранить в бд и вернуться в Meals
    //        Meal meal=mealRestController.get(getId(request));
//        request.setAttribute("meal", meal);
    //        model.addAttribute("meal",mealRestController.create(meal));
//        model.addAttribute("meals",mealRestController.getAll());

//    @PostMapping(value = "/save")
//    public String saveMeal(@ModelAttribute Meal meal, @PathVariable Integer id)
//    {
//        mealRestController.create(meal);
//        return "redirect:/meals";
//    }


    //todo save сделан ( когда есть id & когда нет )
    @PostMapping(value = "/save")
    public String saveMeal(HttpServletRequest request)
    {
        if (request.getParameter("id").isEmpty())
        {
            final Meal meal = new Meal(
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));
            mealRestController.create(meal);
        }
        else {
            final Meal meal = new Meal(
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));
            mealRestController.update(meal,getId(request));
        }


        return "redirect:/meals";
    }

    @PostMapping(value = "/filter")
    public String filterMeal(HttpServletRequest request)
    {
        LocalDate startDate = DateTimeUtil.parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = DateTimeUtil.parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = DateTimeUtil.parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = DateTimeUtil.parseLocalTime(request.getParameter("endTime"));
        request.setAttribute("meals", mealRestController.getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
