package com.nourish.planning;

import com.nourish.meal.Meal;
import com.nourish.profile.*;
import org.springframework.stereotype.Component;

@Component
public class MealScoringService {

    public double score(Meal meal, UserProfile profile) {
        double score = 100.0;
        score -= meal.getPreparationTime() * 0.5;
        score -= meal.getEstimatedCost().doubleValue() * 2.0;

        if (profile.getGoal() == Goal.GAIN_MUSCLE) {
            score += meal.getProtein() * 0.8;
        } else if (profile.getGoal() == Goal.LOSE_WEIGHT) {
            score += meal.getProtein() * 0.4;
        }
        return score;
    }
}
