package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id and m.user.id=:userId")
    int delete(@Param("id") int id,@Param("userId") int userId);
//    @Query(name = User.DELETE)
//    @Query("DELETE FROM Meal m WHERE m.id=?1 and m.user.id=?2")
//    int delete(int id,int userId);

    List<Meal> findAllByUserId(int userId);

    List<Meal> getAllByUserIdAndDateTimeBetweenOrderByDateTimeDesc(int userId,LocalDateTime startDate, LocalDateTime endDate );

    Meal findByUserIdAndId(int userId,int id);




}
