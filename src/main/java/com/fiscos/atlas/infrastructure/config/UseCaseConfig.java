package com.fiscos.atlas.infrastructure.config;

import com.fiscos.atlas.core.application.ports.output.PasswordEncoderPort;
import com.fiscos.atlas.core.application.ports.output.TokenServicePort;
import com.fiscos.atlas.core.application.usecase.*;
import com.fiscos.atlas.core.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository repository, PasswordEncoderPort passwordEncoder) {
        return new CreateUserUseCase(repository, passwordEncoder);
    }

    @Bean
    public GetUserByIdUseCase getUserByIdUseCase(UserRepository repository) {
        return new GetUserByIdUseCase(repository);
    }

    @Bean
    public LoginUseCase loginUseCase(UserRepository repository,
                                     PasswordEncoderPort passwordEncoder,
                                     TokenServicePort tokenService) {
        return new LoginUseCase(repository, passwordEncoder, tokenService);
    }

    @Bean
    public LogoutUseCase logoutUseCase() {
        return new LogoutUseCase();
    }

    @Bean
    public CreateAdminUseCase createAdminUseCase(UserRepository repository, PasswordEncoderPort passwordEncoder) {
        return new CreateAdminUseCase(repository, passwordEncoder);
    }
}