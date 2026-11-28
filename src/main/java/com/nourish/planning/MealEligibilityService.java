package com.nourish.planning;

import com.nourish.meal.*;
import com.nourish.profile.*;
import org.springframework.stereotype.Component;

@Component
public class MealEligibilityService {

    public boolean isDietCompatible(
            DietaryPreference preference, DietType dietType) {
        return switch (preference) {
            case ALL -> true;
            case VEGETARIAN ->
                    dietType == DietType.VEGETARIAN || dietType == DietType.VEGAN;
            case VEGAN -> dietType == DietType.VEGAN;
        };
    }

    public boolean isWithinCookingLimit(Meal meal, int maxMinutes) {
        return meal.getPreparationTime() <= maxMinutes;
    }
}
