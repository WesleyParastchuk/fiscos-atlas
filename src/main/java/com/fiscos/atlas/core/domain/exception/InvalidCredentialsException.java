package com.fiscos.atlas.core.domain.exception;

public class InvalidCredentialsException extends BusinessException {
    public InvalidCredentialsException() {
        super("E-mail ou senha incorretos.");
    }
}