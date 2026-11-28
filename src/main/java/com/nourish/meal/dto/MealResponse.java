package com.nourish.meal.dto;

import com.nourish.meal.*;
import java.math.BigDecimal;

public record MealResponse(
        Long id, String name, MealType mealType, DietType dietType,
        Integer preparationTime, Integer calories, Integer protein,
        Integer carbohydrates, Integer fat, BigDecimal estimatedCost
) {}
