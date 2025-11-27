package com.fiscos.atlas.core.domain.model.vo;

import com.fiscos.atlas.core.domain.exception.WeakPasswordException;

public record RawPassword(String value) {
    
    // Regex explicada:
    // ^                 -> Início
    // (?=.*\d)          -> Pelo menos um dígito (0-9)
    // (?=.*[a-z])       -> Pelo menos uma letra minúscula
    // (?=.*[A-Z])       -> Pelo menos uma letra maiúscula
    // .{8,}             -> Pelo menos 8 caracteres de qualquer tipo
    // $                 -> Fim
    private static final String PASSWORD_PATTERN = 
            "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";

    public RawPassword {
        if (value == null || !value.matches(PASSWORD_PATTERN)) {
            throw new WeakPasswordException(
                "A senha deve ter no mínimo 8 caracteres, contendo números, letras maiúsculas e minúsculas."
            );
        }
    }
}