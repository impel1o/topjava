package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by d.baskakov on 29.03.2017.
 */
public class MealDaoImpl implements MealDao {
    private static Map<Integer,Meal> Db= new HashMap<>();
    static
    {
        Db.put(1,new Meal(1, LocalDateTime.of(2015, Month.MAY, 27, 10, 0), "Завтрак", 999));
        Db.put(2,new Meal(2, LocalDateTime.of(2015, Month.MAY, 28, 13, 0), "Обед", 999));
        Db.put(3,new Meal(3, LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 999));
    }

    private static int autoIndex=4;

    @Override
    public void addMeal(Meal meal) {
        Db.put(meal.getId(),meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        Db.put(meal.getId(),meal);
    }

    @Override
    public void deleteMeal(int id) {
        Db.remove(id);
    }

    @Override
    public List<Meal> getAllMeals() {
        List<Meal> mealList=Db.entrySet().stream()
                .map(x -> x.getValue())
                .collect(Collectors.toList());
        return mealList;
    }

    @Override
    public Meal getMealById(int mealId) {
        return Db.values().stream().filter(x -> x.getId()==mealId).findAny().orElse(null);

    }

    @Override
    public List<MealWithExceed> getAllMealsWithExceed() {
        return MealsUtil.getFilteredWithExceeded(getAllMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
    }
}
