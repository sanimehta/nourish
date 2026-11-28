package com.nourish.profile;

import com.nourish.profile.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {
    private final UserProfileService service;

    public UserProfileController(UserProfileService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse create(@Valid @RequestBody CreateProfileRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public ProfileResponse get(@PathVariable("id") Long id) {
        return service.get(id);
    }
}
