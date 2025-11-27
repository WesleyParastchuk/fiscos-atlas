package com.fiscos.atlas.infrastructure.adapters.output.persistence;

import com.fiscos.atlas.core.domain.model.aggregate.User;
import com.fiscos.atlas.core.domain.repository.UserRepository;
import com.fiscos.atlas.infrastructure.adapters.output.persistence.entity.UserEntity;
import com.fiscos.atlas.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import com.fiscos.atlas.infrastructure.adapters.output.persistence.repository.SpringDataUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserPersistenceAdapter implements UserRepository {

    private final SpringDataUserRepository springRepo;
    private final UserPersistenceMapper mapper;

    public UserPersistenceAdapter(SpringDataUserRepository springRepo, UserPersistenceMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public void save(User user) {
        UserEntity entity = mapper.toEntity(user);
        springRepo.save(entity);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return springRepo.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springRepo.findByEmail(email).map(mapper::toDomain);
    }
}