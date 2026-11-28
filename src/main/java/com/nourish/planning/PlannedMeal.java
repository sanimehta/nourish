package com.nourish.planning;

import com.nourish.meal.Meal;
import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "planned_meals")
public class PlannedMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private MealPlan mealPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @Column(name = "scheduled_time", nullable = false)
    private LocalTime scheduledTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MealStatus status;

    protected PlannedMeal() {}

    public PlannedMeal(MealPlan plan, Meal meal, LocalTime time) {
        this.mealPlan = plan;
        this.meal = meal;
        this.scheduledTime = time;
        this.status = MealStatus.SCHEDULED;
    }

    public Long getId() { return id; }
    public MealPlan getMealPlan() { return mealPlan; }
    public Meal getMeal() { return meal; }
    public LocalTime getScheduledTime() { return scheduledTime; }
    public MealStatus getStatus() { return status; }
    public void setStatus(MealStatus status) { this.status = status; }
}
