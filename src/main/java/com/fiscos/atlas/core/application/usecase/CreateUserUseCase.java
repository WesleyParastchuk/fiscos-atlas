package com.fiscos.atlas.core.application.usecase;

import com.fiscos.atlas.core.application.dto.CreateUserRequest;
import com.fiscos.atlas.core.application.dto.UserResponse;
import com.fiscos.atlas.core.application.mapper.UserDataMapper;
import com.fiscos.atlas.core.application.ports.output.PasswordEncoderPort;
import com.fiscos.atlas.core.domain.exception.EmailAlreadyExistsException;
import com.fiscos.atlas.core.domain.model.aggregate.User;
import com.fiscos.atlas.core.domain.model.vo.RawPassword;
import com.fiscos.atlas.core.domain.repository.UserRepository;

public class CreateUserUseCase {

    private final UserRepository repository;
    private final PasswordEncoderPort passwordEncoder;

    public CreateUserUseCase(UserRepository repository,
                             PasswordEncoderPort passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse execute(CreateUserRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(request.email());
        }

        RawPassword rawPassword = new RawPassword(request.password());

        String encodedPassword = passwordEncoder.encode(rawPassword.value());

        User user = User.create(request.name(), request.email(), encodedPassword);
        repository.save(user);

        return UserDataMapper.toResponse(user);
    }
}