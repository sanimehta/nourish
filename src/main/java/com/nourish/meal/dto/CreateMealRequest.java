package com.nourish.meal.dto;

import com.nourish.meal.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record CreateMealRequest(
        @NotBlank String name,
        @NotNull MealType mealType,
        @NotNull DietType dietType,
        @NotNull @Min(1) Integer preparationTime,
        @NotNull @Min(0) Integer calories,
        @NotNull @Min(0) Integer protein,
        @NotNull @Min(0) Integer carbohydrates,
        @NotNull @Min(0) Integer fat,
        @NotNull @DecimalMin("0.00") BigDecimal estimatedCost
) {}
