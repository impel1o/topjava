package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Meal> repositoryMeal = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        LOG.info("new id "+meal.getId()+" userID "+meal.getUserId());
        repositoryMeal.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id,int userId) {
//        LOG.info("DATA "+repositoryMeal.get(id).getId());

        if (repositoryMeal.get(id).getUserId()==userId) {
            repositoryMeal.remove(id);
//            LOG.info("delete " + id);
            return true;
        }
//        LOG.info("NE YDALOS' delete " + id);
        return false;
    }

    @Override
    public Meal get(int id,int userId) {
        LOG.info("get " + id);
//        if (!repositoryMeal.containsKey(id)) {
//            return null;
//        }
        if (repositoryMeal.get(id).getUserId()==userId)
        {
            return repositoryMeal.get(id);
        }
        return null;
    }

    @Override
    public List<Meal> getAll() {
        LOG.info("getAll");
        if (repositoryMeal.isEmpty()) return Collections.emptyList();
        else {
            //TODO добавить сортировку по времени , последние записи наверху
//            sorted(x-> Comparator.comparing(x.getDateTime())).
            return repositoryMeal.values().stream().collect(Collectors.toList());
        }
    }
}

