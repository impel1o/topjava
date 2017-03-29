
package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.db.MealsDB;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

/**
 * Created by d.baskakov on 28.03.2017.
 */
public class MealDaoListImpl implements MealDaoList {
    @Override

    //TODO убрать ввод ID, autoincrement
    public void addMeal(Meal meal) {
        MealsDB.getListMeals().add(MealsDB.getIndex(),meal);
        MealsDB.setIndex(MealsDB.getIndex()+1);
    }

    @Override
    public void updateMeal(Meal meal) {
//        getMealById(meal.getId()).setCalories(meal.getCalories());
        MealsDB.getListMeals().set(meal.getId()-1,meal);
    }

    @Override
    public void deleteMeal(int id) {
        Iterator<Meal> it = MealsDB.getListMeals().iterator();

        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                break;
            }
        }

    }

    @Override
    public List<Meal> getAllMeals() {
        return MealsDB.getListMeals();
    }

    @Override
    public Meal getMealById(int mealId) {
        Iterator<Meal> it = MealsDB.getListMeals().iterator();
        Meal meal;
        while (it.hasNext()) {
            meal=it.next();
            if (meal.getId() == mealId) {
                return meal;
            }
        }
        return null;
    }

    @Override
    public List<MealWithExceed> getAllMealsWithExceed() {
        return MealsUtil.getFilteredWithExceeded(MealsDB.getListMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
    }

}