CREATE TABLE meals (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(180) NOT NULL,
    meal_type VARCHAR(30) NOT NULL,
    diet_type VARCHAR(30) NOT NULL,
    preparation_time INTEGER NOT NULL CHECK (preparation_time > 0),
    calories INTEGER NOT NULL CHECK (calories >= 0),
    protein INTEGER NOT NULL CHECK (protein >= 0),
    carbohydrates INTEGER NOT NULL CHECK (carbohydrates >= 0),
    fat INTEGER NOT NULL CHECK (fat >= 0),
    estimated_cost NUMERIC(10,2) NOT NULL CHECK (estimated_cost >= 0)
);
CREATE INDEX idx_meals_type ON meals(meal_type);
CREATE INDEX idx_meals_diet_type ON meals(diet_type);
