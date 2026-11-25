package com.nourish.profile.dto;

import com.nourish.profile.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record CreateProfileRequest(
        @NotBlank @Email String email,
        @NotNull Goal goal,
        @NotNull DietaryPreference dietaryPreference,
        @NotNull @Min(1) Integer dailyCalorieTarget,
        @NotNull @Min(1) Integer dailyProteinTarget,
        @NotNull @DecimalMin("0.00") BigDecimal dailyBudget
) {}
