package com.fiscos.atlas.infrastructure.adapters.input.rest;

import com.fiscos.atlas.core.application.dto.CreateUserRequest;
import com.fiscos.atlas.core.application.dto.UserResponse;
import com.fiscos.atlas.core.application.usecase.CreateAdminUseCase;
import com.fiscos.atlas.infrastructure.config.annotations.IsAdmin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CreateAdminUseCase createAdminUseCase;

    public AdminController(CreateAdminUseCase createAdminUseCase) {
        this.createAdminUseCase = createAdminUseCase;
    }

    @IsAdmin
    @PostMapping("/users")
    public ResponseEntity<UserResponse> createAdmin(@RequestBody CreateUserRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createAdminUseCase.execute(request));
    }
}