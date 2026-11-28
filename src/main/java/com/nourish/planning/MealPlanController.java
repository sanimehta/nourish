package com.nourish.planning;

import com.nourish.planning.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plans")
public class MealPlanController {
    private final MealPlanService service;

    public MealPlanController(MealPlanService service) {
        this.service = service;
    }

    @PostMapping("/generate/{profileId}")
    @ResponseStatus(HttpStatus.CREATED)
    public MealPlanResponse generate(@PathVariable("profileId") Long profileId,
                                     @Valid @RequestBody GeneratePlanRequest request) {
        return service.generate(profileId, request);
    }

    @GetMapping("/{planId}")
    public MealPlanResponse get(@PathVariable("planId") Long planId) {
        return service.get(planId);
    }
}
