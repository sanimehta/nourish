CREATE TABLE user_profiles (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    goal VARCHAR(40) NOT NULL,
    dietary_preference VARCHAR(40) NOT NULL,
    daily_calorie_target INTEGER NOT NULL CHECK (daily_calorie_target > 0),
    daily_protein_target INTEGER NOT NULL CHECK (daily_protein_target > 0),
    daily_budget NUMERIC(10,2) NOT NULL CHECK (daily_budget >= 0)
);
