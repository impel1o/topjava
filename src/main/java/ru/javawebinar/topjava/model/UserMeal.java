package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public LocalDate getDate() {
        return date;
    }

    private final LocalDate date;

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.date = dateTime.toLocalDate();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }
}
