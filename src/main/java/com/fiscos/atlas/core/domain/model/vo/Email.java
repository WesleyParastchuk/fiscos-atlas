package com.fiscos.atlas.core.domain.model.vo;

import com.fiscos.atlas.core.domain.exception.InvalidEmailException;

public record Email(String value) {
    public Email {
        if (value == null || !value.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new InvalidEmailException(value);
        }
    }
}