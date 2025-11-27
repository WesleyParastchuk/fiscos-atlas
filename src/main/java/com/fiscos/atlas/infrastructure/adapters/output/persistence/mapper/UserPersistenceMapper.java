package com.fiscos.atlas.infrastructure.adapters.output.persistence.mapper;

import com.fiscos.atlas.core.domain.model.aggregate.User;
import com.fiscos.atlas.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {

    public UserEntity toEntity(User user) {
        return new UserEntity(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getPassword(),
            user.getRole()
        );
    }

    public User toDomain(UserEntity entity) {
        return User.restore(
            entity.getId(),
            entity.getName(),
            entity.getEmail(),
            entity.getPassword(),
            entity.getRole()
        );
    }
}