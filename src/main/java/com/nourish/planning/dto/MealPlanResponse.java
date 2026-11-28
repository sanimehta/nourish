package com.nourish.planning.dto;

import com.nourish.planning.PlanStatus;
import java.time.LocalDate;
import java.util.List;

public record MealPlanResponse(
        Long id, LocalDate date, PlanStatus status,
        List<PlannedMealResponse> meals,
        Integer totalCalories, Integer totalProtein,
        Integer totalCarbohydrates, Integer totalFat
) {}
