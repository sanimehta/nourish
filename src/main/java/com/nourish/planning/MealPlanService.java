package com.nourish.planning;

import com.nourish.meal.*;
import com.nourish.profile.*;
import com.nourish.planning.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;

@Service
public class MealPlanService {
    private final MealPlanRepository planRepo;
    private final PlannedMealRepository plannedRepo;
    private final UserProfileRepository profileRepo;
    private final MealRepository mealRepo;
    private final MealEligibilityService eligibility;
    private final MealScoringService scoring;

    public MealPlanService(MealPlanRepository planRepo,
                           PlannedMealRepository plannedRepo,
                           UserProfileRepository profileRepo,
                           MealRepository mealRepo,
                           MealEligibilityService eligibility,
                           MealScoringService scoring) {
        this.planRepo = planRepo;
        this.plannedRepo = plannedRepo;
        this.profileRepo = profileRepo;
        this.mealRepo = mealRepo;
        this.eligibility = eligibility;
        this.scoring = scoring;
    }

    @Transactional
    public MealPlanResponse generate(Long profileId, GeneratePlanRequest request) {
        UserProfile profile = profileRepo.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        if (planRepo.findByProfileIdAndPlanDate(profileId, request.date()).isPresent()) {
            throw new IllegalStateException("A plan already exists for this date");
        }

        List<Meal> candidates = mealRepo.findAll().stream()
                .filter(m -> eligibility.isDietCompatible(
                        profile.getDietaryPreference(), m.getDietType()))
                .filter(m -> eligibility.isWithinCookingLimit(
                        m, request.maxCookingTime()))
                .toList();

        List<MealType> requiredTypes = List.of(
                MealType.BREAKFAST, MealType.LUNCH, MealType.DINNER);
        List<LocalTime> times = List.of(
                LocalTime.of(8, 0), LocalTime.of(13, 0), LocalTime.of(19, 0));

        List<Meal> selected = new ArrayList<>();
        BigDecimal remainingBudget = request.availableBudget();

        for (MealType type : requiredTypes) {
            final BigDecimal budgetSnapshot = remainingBudget;
            Meal best = candidates.stream()
                    .filter(m -> m.getMealType() == type)
                    .filter(m -> m.getEstimatedCost().compareTo(budgetSnapshot) <= 0)
                    .filter(m -> !selected.contains(m))
                    .max(Comparator.comparingDouble(m -> scoring.score(m, profile)))
                    .orElseThrow(() -> new IllegalStateException(
                            "No suitable meal available for " + type));
            selected.add(best);
            remainingBudget = remainingBudget.subtract(best.getEstimatedCost());
        }

        MealPlan plan = planRepo.save(new MealPlan(profile, request.date()));
        List<PlannedMeal> planned = new ArrayList<>();
        for (int i = 0; i < selected.size(); i++) {
            planned.add(new PlannedMeal(plan, selected.get(i), times.get(i)));
        }
        plannedRepo.saveAll(planned);
        return toResponse(plan, planned);
    }

    @Transactional(readOnly = true)
    public MealPlanResponse get(Long planId) {
        MealPlan plan = planRepo.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("Plan not found"));
        return toResponse(plan, plannedRepo.findByMealPlanId(planId));
    }

    private MealPlanResponse toResponse(MealPlan plan, List<PlannedMeal> meals) {
        List<PlannedMealResponse> items = meals.stream().map(pm ->
                new PlannedMealResponse(pm.getId(), pm.getMeal().getName(),
                        pm.getMeal().getMealType(), pm.getScheduledTime(),
                        pm.getMeal().getCalories(), pm.getMeal().getProtein(),
                        pm.getMeal().getCarbohydrates(), pm.getMeal().getFat(),
                        pm.getMeal().getPreparationTime(), pm.getStatus())
        ).toList();

        return new MealPlanResponse(plan.getId(), plan.getPlanDate(),
                plan.getStatus(), items,
                meals.stream().mapToInt(m -> m.getMeal().getCalories()).sum(),
                meals.stream().mapToInt(m -> m.getMeal().getProtein()).sum(),
                meals.stream().mapToInt(m -> m.getMeal().getCarbohydrates()).sum(),
                meals.stream().mapToInt(m -> m.getMeal().getFat()).sum());
    }
}
