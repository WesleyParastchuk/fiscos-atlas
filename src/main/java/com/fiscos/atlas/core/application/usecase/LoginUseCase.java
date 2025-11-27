package com.fiscos.atlas.core.application.usecase;

import com.fiscos.atlas.core.application.dto.LoginRequest;
import com.fiscos.atlas.core.application.dto.LoginResponse;
import com.fiscos.atlas.core.application.dto.UserResponse;
import com.fiscos.atlas.core.application.mapper.UserDataMapper;
import com.fiscos.atlas.core.application.ports.output.PasswordEncoderPort;
import com.fiscos.atlas.core.application.ports.output.TokenServicePort;
import com.fiscos.atlas.core.domain.exception.InvalidCredentialsException;
import com.fiscos.atlas.core.domain.model.aggregate.User;
import com.fiscos.atlas.core.domain.repository.UserRepository;

public class LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final TokenServicePort tokenService;

    public LoginUseCase(UserRepository userRepository, 
                        PasswordEncoderPort passwordEncoder, 
                        TokenServicePort tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public LoginResponse execute(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String token = tokenService.generateToken(user);

        UserResponse userResponse = UserDataMapper.toResponse(user);

        return new LoginResponse(token, userResponse);
    }
}