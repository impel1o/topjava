package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

/**
 * Created by d.baskakov on 28.03.2017.
 */
public interface MealDao {
    void addMeal(Meal meal);
    void updateMeal(Meal meal);
    void deleteMeal(int id);
    List<Meal> getAllMeals();
    Meal getMealById(int mealId);
    List<MealWithExceed> getAllMealsWithExceed();
}
