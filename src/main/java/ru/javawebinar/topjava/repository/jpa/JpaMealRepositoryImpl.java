package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {

        //это ленивая обертка , а можн было сделать и через NamedQueries
        //TODO не проходит тест NotFound, надо сделать NamedQuery

//        if (meal.getUser().getId()!=userId) return null;
        User ref = em.getReference(User.class, userId);
        meal.setUser(ref);
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            return em.merge(meal);
        }

    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        //my gavno kod
//        if (get(id,userId).getUser().getId()==userId)
//        {
//            Meal ref = em.getReference(Meal.class, id);
//            em.remove(ref);
//            return true;
//        } else return false;

        // пример NamedQuery
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> meals=em.createNamedQuery(Meal.FIND)
                .setParameter("id", id)
                .setParameter("userId", userId).getResultList();
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED,Meal.class).setParameter("userId",userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.FILTER_DATE,Meal.class)
                .setParameter("userId",userId)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .getResultList();
    }
}