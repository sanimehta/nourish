package com.nourish.planning.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record GeneratePlanRequest(
        @NotNull @FutureOrPresent LocalDate date,
        @NotNull @Min(1) Integer maxCookingTime,
        @NotNull @DecimalMin("0.01") BigDecimal availableBudget
) {}
