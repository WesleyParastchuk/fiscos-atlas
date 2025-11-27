package com.fiscos.atlas.core.application.dto;

import com.fiscos.atlas.core.domain.model.vo.Role;

import java.util.UUID;

public record UserResponse(UUID id, String name, String email, Role role) {}