package com.nourish.planning.dto;

import com.nourish.meal.MealType;
import com.nourish.planning.MealStatus;
import java.time.LocalTime;

public record PlannedMealResponse(
        Long id, String mealName, MealType mealType, LocalTime scheduledTime,
        Integer calories, Integer protein, Integer carbohydrates, Integer fat,
        Integer preparationTime, MealStatus status
) {}
