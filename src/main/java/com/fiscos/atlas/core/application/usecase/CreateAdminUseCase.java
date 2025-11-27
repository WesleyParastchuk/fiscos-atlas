package com.fiscos.atlas.core.application.usecase;

import com.fiscos.atlas.core.application.dto.CreateUserRequest; // Reaproveitamos o DTO
import com.fiscos.atlas.core.application.dto.UserResponse;
import com.fiscos.atlas.core.application.mapper.UserDataMapper;
import com.fiscos.atlas.core.application.ports.output.PasswordEncoderPort;
import com.fiscos.atlas.core.domain.exception.EmailAlreadyExistsException;
import com.fiscos.atlas.core.domain.model.aggregate.User;
import com.fiscos.atlas.core.domain.repository.UserRepository;

public class CreateAdminUseCase {

    private final UserRepository repository;
    private final PasswordEncoderPort passwordEncoder;

    public CreateAdminUseCase(UserRepository repository, PasswordEncoderPort passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse execute(CreateUserRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(request.email());
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        User admin = User.createAdmin(request.name(), request.email(), encodedPassword);
        
        repository.save(admin);

        return UserDataMapper.toResponse(admin);
    }
}