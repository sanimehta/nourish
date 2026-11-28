package com.nourish.planning;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
    Optional<MealPlan> findByProfileIdAndPlanDate(Long profileId, LocalDate planDate);
}

