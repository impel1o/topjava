package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.09.2015.
 */
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id,int userId) {
        LOG.info("DATA "+repository.get(id).getId());

        if (repository.get(id).getUserId()==userId) {
            repository.remove(id);
            LOG.info("delete " + id);
            return true;
        }
        LOG.info("NE YDALOS' delete " + id);
        return false;
    }

    @Override
    public Meal get(int id,int userId) {
        LOG.info("get " + id);
//        if (!repository.containsKey(id)) {
//            return null;
//        }
        if (repository.get(id).getUserId()==userId)
        {
            return repository.get(id);
        }
        return null;
    }

    @Override
    public Collection<Meal> getAll() {
        LOG.info("getAll");
        if (repository.isEmpty()) return Collections.emptyList();
        else {
            //TODO добавить сортировку по времени , последние записи наверху
            return repository.values();
        }
    }
}

