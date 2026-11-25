package com.nourish.profile;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Goal goal;

    @Enumerated(EnumType.STRING)
    @Column(name = "dietary_preference", nullable = false)
    private DietaryPreference dietaryPreference;

    @Column(name = "daily_calorie_target", nullable = false)
    private Integer dailyCalorieTarget;

    @Column(name = "daily_protein_target", nullable = false)
    private Integer dailyProteinTarget;

    @Column(name = "daily_budget", nullable = false, precision = 10, scale = 2)
    private BigDecimal dailyBudget;

    protected UserProfile() {}

    public UserProfile(String email, Goal goal,
                       DietaryPreference dietaryPreference,
                       Integer dailyCalorieTarget,
                       Integer dailyProteinTarget, BigDecimal dailyBudget) {
        this.email = email;
        this.goal = goal;
        this.dietaryPreference = dietaryPreference;
        this.dailyCalorieTarget = dailyCalorieTarget;
        this.dailyProteinTarget = dailyProteinTarget;
        this.dailyBudget = dailyBudget;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public Goal getGoal() { return goal; }
    public DietaryPreference getDietaryPreference() { return dietaryPreference; }
    public Integer getDailyCalorieTarget() { return dailyCalorieTarget; }
    public Integer getDailyProteinTarget() { return dailyProteinTarget; }
    public BigDecimal getDailyBudget() { return dailyBudget; }
}
