package com.nourish.meal;

import com.nourish.meal.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {
    private final MealService service;

    public MealController(MealService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MealResponse create(@Valid @RequestBody CreateMealRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<MealResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MealResponse get(@PathVariable("id") Long id) {
        return service.get(id);
    }
    @PutMapping("/{id}")
    public MealResponse update(@PathVariable("id") Long id,
                               @Valid @RequestBody CreateMealRequest request) {
        return service.update(id, request);
    }
}
