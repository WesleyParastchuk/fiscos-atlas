package com.fiscos.atlas.core.domain.repository;

import com.fiscos.atlas.core.domain.model.aggregate.User;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(UUID id);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}