package ru.javawebinar.topjava.db;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by d.baskakov on 28.03.2017.
 */
public class MealsDB {
    private static List<Meal> listMeals = new ArrayList<>();
    private static int index=6;

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        MealsDB.index = index;
    }

    static
    {
             listMeals.add(new Meal(1, LocalDateTime.of(2015, Month.MAY, 27, 10, 0), "Завтрак", 999));
        listMeals.add(new Meal(2, LocalDateTime.of(2015, Month.MAY, 28, 13, 0), "Обед", 999));
        listMeals.add(new Meal(3, LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 999));
        listMeals.add(new Meal(4, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 999));
        listMeals.add(new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 999));
        listMeals.add(new Meal(6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 999));
    }

//            Arrays.asList(
//            new Meal(1, LocalDateTime.of(2015, Month.MAY, 27, 10, 0), "Завтрак", 999),
//            new Meal(2, LocalDateTime.of(2015, Month.MAY, 28, 13, 0), "Обед", 999),
//            new Meal(3, LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 999),
//            new Meal(4, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 999),
//            new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 999),
//            new Meal(6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 999)
//    );


//    public static void main(String[] args) {
//
//        int id = 300;
//        System.out.println(listMeals.get(1).getCalories());
//        listMeals.get(1).setCalories(id);
//        System.out.println(listMeals.get(1).getCalories());
//    }

    public static List<Meal> getListMeals() {
        return listMeals;
    }
}
