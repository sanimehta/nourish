package com.nourish.meal;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type", nullable = false)
    private MealType mealType;

    @Enumerated(EnumType.STRING)
    @Column(name = "diet_type", nullable = false)
    private DietType dietType;

    @Column(name = "preparation_time", nullable = false)
    private Integer preparationTime;
    private Integer calories;
    private Integer protein;
    private Integer carbohydrates;
    private Integer fat;

    @Column(name = "estimated_cost", precision = 10, scale = 2)
    private BigDecimal estimatedCost;

    protected Meal() {}

    public Meal(String name, MealType mealType, DietType dietType,
                Integer preparationTime, Integer calories, Integer protein,
                Integer carbohydrates, Integer fat, BigDecimal estimatedCost) {
        this.name = name;
        this.mealType = mealType;
        this.dietType = dietType;
        this.preparationTime = preparationTime;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.estimatedCost = estimatedCost;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public MealType getMealType() { return mealType; }
    public DietType getDietType() { return dietType; }
    public Integer getPreparationTime() { return preparationTime; }
    public Integer getCalories() { return calories; }
    public Integer getProtein() { return protein; }
    public Integer getCarbohydrates() { return carbohydrates; }
    public Integer getFat() { return fat; }
    public BigDecimal getEstimatedCost() { return estimatedCost; }

    public void update(String name, MealType mealType, DietType dietType,
                       Integer preparationTime, Integer calories, Integer protein,
                       Integer carbohydrates, Integer fat, BigDecimal estimatedCost) {
        this.name = name;
        this.mealType = mealType;
        this.dietType = dietType;
        this.preparationTime = preparationTime;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.estimatedCost = estimatedCost;
    }
}
