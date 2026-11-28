CREATE TABLE meal_plans (
                            id BIGSERIAL PRIMARY KEY,
                            profile_id BIGINT NOT NULL REFERENCES user_profiles(id),
                            plan_date DATE NOT NULL,
                            status VARCHAR(30) NOT NULL,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            UNIQUE (profile_id, plan_date)
);

CREATE TABLE planned_meals (
                               id BIGSERIAL PRIMARY KEY,
                               plan_id BIGINT NOT NULL REFERENCES meal_plans(id) ON DELETE CASCADE,
                               meal_id BIGINT NOT NULL REFERENCES meals(id),
                               scheduled_time TIME NOT NULL,
                               status VARCHAR(30) NOT NULL
);
CREATE INDEX idx_planned_meals_plan_id ON planned_meals(plan_id);
