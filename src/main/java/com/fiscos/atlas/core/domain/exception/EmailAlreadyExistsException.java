package com.fiscos.atlas.core.domain.exception;

public class EmailAlreadyExistsException extends BusinessException {
    public EmailAlreadyExistsException(String email) {
        super("O e-mail '" + email + "' já está cadastrado.");
    }
}