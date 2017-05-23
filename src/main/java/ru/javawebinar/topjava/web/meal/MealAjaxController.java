package ru.javawebinar.topjava.web.meal;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by d.baskakov on 22.05.2017.
 */

@RestController
@RequestMapping(value = "/ajax/meals")
public class MealAjaxController extends AbstractMealController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll()
    {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(HttpServletRequest request) {

        String descriprion=request.getParameter("description");

//        if (meal.isNew()) {
//            super.create(meal);
//        }
        //        else {
//            super.update(meal, id);
//        }
    }

}
