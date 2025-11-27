package com.fiscos.atlas.core.application.usecase;

import com.fiscos.atlas.core.application.dto.UserResponse;
import com.fiscos.atlas.core.application.mapper.UserDataMapper;
import com.fiscos.atlas.core.domain.exception.ResourceNotFoundException;
import com.fiscos.atlas.core.domain.repository.UserRepository;

import java.util.UUID;

public class GetUserByIdUseCase {

    private final UserRepository repository;

    public GetUserByIdUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponse execute(UUID id) {
        return repository.findById(id)
                .map(UserDataMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
    }
}