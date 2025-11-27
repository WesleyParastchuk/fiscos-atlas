package com.fiscos.atlas.core.application.ports.output;


import com.fiscos.atlas.core.domain.model.aggregate.User;

import java.util.Map;

public interface TokenServicePort {
    String generateToken(User user);
    String validateToken(String token);
    Map<String, Object> extractAllClaims(String token);
}