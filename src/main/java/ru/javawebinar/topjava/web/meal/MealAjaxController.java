package ru.javawebinar.topjava.web.meal;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

/**
 * Created by d.baskakov on 22.05.2017.
 */

@RestController
@RequestMapping(value = "/ajax/meals")
public class MealAjaxController extends AbstractMealController {


//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<MealWithExceed> getAll(HttpServletRequest request) {
//
//        LocalDate startDate=LocalDate.parse((String)request.getSession().getAttribute("startDate"));
//        LocalTime startTime=LocalTime.parse((String)request.getSession().getAttribute("startTime"));
//        LocalDate endDate=LocalDate.parse((String)request.getSession().getAttribute("endDate"));
//        LocalTime endTime=LocalTime.parse((String)request.getSession().getAttribute("endTime"));
//        return super.getBetween(startDate, startTime, endDate, endTime);
//    }
//
//    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
//    public void filter(HttpServletRequest request) {
//        request.getSession().setAttribute("startDate", request.getParameter("startDate"));
//        request.getSession().setAttribute("startTime", request.getParameter("startTime"));
//        request.getSession().setAttribute("endDate", request.getParameter("endDate"));
//        request.getSession().setAttribute("endTime", request.getParameter("endTime"));
//    }

    @PostMapping(value = "/filter")
    public void filter(HttpServletRequest request,
                       @RequestParam(value = "startDate", required = false) LocalDate startDate,
                       @RequestParam(value = "startTime", required = false) LocalTime startTime,
                       @RequestParam(value = "endDate", required = false) LocalDate endDate,
                       @RequestParam(value = "endTime", required = false) LocalTime endTime) {
        request.getSession().setAttribute("startDate", startDate);
        request.getSession().setAttribute("startTime", startTime);
        request.getSession().setAttribute("endDate", endDate);
        request.getSession().setAttribute("endTime", endTime);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll(HttpServletRequest request) {
        return super.getBetween(
                (LocalDate) request.getSession().getAttribute("startDate"),
                (LocalTime) request.getSession().getAttribute("startTime"),
                (LocalDate) request.getSession().getAttribute("endDate"),
                (LocalTime) request.getSession().getAttribute("endTime")
        );
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("description") String desciption,
                               @RequestParam("calories") int calories) {
        final Meal meal = new Meal(LocalDateTime.now(), desciption, calories);
        super.create(meal);
    }

//    @PostMapping(value="/filter",produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<MealWithExceed> getBetween(
//            @RequestParam(value = "startDate", required = false) LocalDate startDate, @RequestParam(value = "startTime", required = false) LocalTime startTime,
//            @RequestParam(value = "endDate", required = false) LocalDate endDate, @RequestParam(value = "endTime", required = false) LocalTime endTime) {
//        return super.getBetween(startDate, startTime, endDate, endTime);
//    }



}
