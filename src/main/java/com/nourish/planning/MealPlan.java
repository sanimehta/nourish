package com.nourish.planning;

import com.nourish.profile.UserProfile;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "meal_plans",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_profile_plan_date",
                columnNames = {"profile_id", "plan_date"}))
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private UserProfile profile;

    @Column(name = "plan_date", nullable = false)
    private LocalDate planDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected MealPlan() {}

    public MealPlan(UserProfile profile, LocalDate planDate) {
        this.profile = profile;
        this.planDate = planDate;
        this.status = PlanStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public UserProfile getProfile() { return profile; }
    public LocalDate getPlanDate() { return planDate; }
    public PlanStatus getStatus() { return status; }
}
