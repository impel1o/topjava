package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import static ru.javawebinar.topjava.util.ValidationUtil.checkIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

import java.util.List;

@Controller
public class MealRestController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    private MealService service;


    public Meal save(Meal meal)
    {
        service.save(meal);
        return meal;
    }

    public Meal get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public void delete(int id,int userId) {
//        LOG.info("delete " + id);
        service.delete(id,userId);
    }

    public Meal create(Meal meal) {
        LOG.info("create " + meal);
        checkNew(meal);
        return service.save(meal);
    }

    public List<MealWithExceed> getAll() {
        LOG.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(),  2000);
    }

    public void update(Meal meal, int id) {
        LOG.info("update " + meal);
        checkIdConsistent(meal, id);
        service.update(meal);
    }



}