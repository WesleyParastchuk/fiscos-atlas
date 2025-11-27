package com.fiscos.atlas.infrastructure.adapters.input.rest;

import com.fiscos.atlas.core.application.dto.LoginRequest;
import com.fiscos.atlas.core.application.dto.LoginResponse;
import com.fiscos.atlas.core.application.usecase.LoginUseCase;
import com.fiscos.atlas.core.application.usecase.LogoutUseCase;
import com.fiscos.atlas.infrastructure.config.annotations.IsAuthenticated;
import com.fiscos.atlas.infrastructure.config.annotations.IsPublic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final LogoutUseCase logoutUseCase;

    public AuthController(LoginUseCase loginUseCase, LogoutUseCase logoutUseCase) {
        this.loginUseCase = loginUseCase;
        this.logoutUseCase = logoutUseCase;
    }

    @IsPublic
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginUseCase.execute(request));
    }

    @IsAuthenticated
    @PostMapping("/logout")
    public ResponseEntity<LoginResponse> logout() {
        logoutUseCase.execute();

        return ResponseEntity.ok(new LoginResponse(null, null));
    }
}