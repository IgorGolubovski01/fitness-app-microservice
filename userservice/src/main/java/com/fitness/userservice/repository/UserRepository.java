package com.fitness.userservice.repository;

import com.fitness.userservice.model.User;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);

    @Nullable Boolean existsByKeycloakId(String keycloakId);

    User findByEmail(String email);
}
