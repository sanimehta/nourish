package com.nourish.profile.dto;

import com.nourish.profile.*;
import java.math.BigDecimal;

public record ProfileResponse(
        Long id, String email, Goal goal,
        DietaryPreference dietaryPreference,
        Integer dailyCalorieTarget, Integer dailyProteinTarget,
        BigDecimal dailyBudget
) {}
