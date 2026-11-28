package com.nourish.meal;

import com.nourish.meal.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MealService {
    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public MealResponse create(CreateMealRequest r) {
        Meal meal = new Meal(r.name(), r.mealType(), r.dietType(),
                r.preparationTime(), r.calories(), r.protein(),
                r.carbohydrates(), r.fat(), r.estimatedCost());
        return toResponse(repository.save(meal));
    }
    @Transactional
    public MealResponse update(Long id, CreateMealRequest r) {
        Meal meal = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Meal not found"));
        meal.update(r.name(), r.mealType(), r.dietType(), r.preparationTime(),
                r.calories(), r.protein(), r.carbohydrates(), r.fat(), r.estimatedCost());
        return toResponse(meal);
    }

    @Transactional(readOnly = true)
    public List<MealResponse> getAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public MealResponse get(Long id) {
        Meal meal = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Meal not found"));
        return toResponse(meal);
    }

    private MealResponse toResponse(Meal m) {
        return new MealResponse(m.getId(), m.getName(), m.getMealType(),
                m.getDietType(), m.getPreparationTime(), m.getCalories(),
                m.getProtein(), m.getCarbohydrates(), m.getFat(),
                m.getEstimatedCost());
    }
}
