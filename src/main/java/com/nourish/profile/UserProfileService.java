package com.nourish.profile;

import com.nourish.profile.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProfileService {
    private final UserProfileRepository repository;

    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ProfileResponse create(CreateProfileRequest request) {
        if (repository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        UserProfile saved = repository.save(new UserProfile(
                request.email(), request.goal(), request.dietaryPreference(),
                request.dailyCalorieTarget(), request.dailyProteinTarget(),
                request.dailyBudget()
        ));
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public ProfileResponse get(Long id) {
        UserProfile profile = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        return toResponse(profile);
    }

    private ProfileResponse toResponse(UserProfile p) {
        return new ProfileResponse(p.getId(), p.getEmail(), p.getGoal(),
                p.getDietaryPreference(), p.getDailyCalorieTarget(),
                p.getDailyProteinTarget(), p.getDailyBudget());
    }
}

