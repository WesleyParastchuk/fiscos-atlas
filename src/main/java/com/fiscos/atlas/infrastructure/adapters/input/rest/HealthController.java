package com.fiscos.atlas.infrastructure.adapters.input.rest;

import com.fiscos.atlas.infrastructure.config.annotations.IsPublic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @IsPublic
    @GetMapping("/")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "service", "Atlas API"
        ));
    }
}