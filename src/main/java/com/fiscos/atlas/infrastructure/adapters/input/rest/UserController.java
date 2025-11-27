package com.fiscos.atlas.infrastructure.adapters.input.rest;

import com.fiscos.atlas.core.application.dto.CreateUserRequest;
import com.fiscos.atlas.core.application.dto.UserResponse;
import com.fiscos.atlas.core.application.usecase.CreateUserUseCase;
import com.fiscos.atlas.core.application.usecase.GetUserByIdUseCase;
import com.fiscos.atlas.infrastructure.config.annotations.IsAuthenticated;
import com.fiscos.atlas.infrastructure.config.annotations.IsPublic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;

    public UserController(CreateUserUseCase createUserUseCase, 
                          GetUserByIdUseCase getUserByIdUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
    }

    @IsPublic
    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserUseCase.execute(request));
    }

    @IsAuthenticated
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable UUID id) {
        return ResponseEntity.ok(getUserByIdUseCase.execute(id));
    }
}