package com.nourish.planning;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlannedMealRepository extends JpaRepository<PlannedMeal, Long> {
    List<PlannedMeal> findByMealPlanId(Long mealPlanId);
}
