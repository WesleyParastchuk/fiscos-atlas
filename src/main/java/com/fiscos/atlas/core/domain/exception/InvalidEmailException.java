package com.fiscos.atlas.core.domain.exception;

public class InvalidEmailException extends BusinessException {
    public InvalidEmailException(String email) {
        super("O formato do e-mail '" + email + "' é inválido.");
    }
}