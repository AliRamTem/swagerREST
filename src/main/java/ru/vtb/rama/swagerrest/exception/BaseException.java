package ru.vtb.rama.swagerrest.exception;

import lombok.Data;

@Data
public abstract class BaseException extends RuntimeException {
    private String message;

    public BaseException(String message) {
        this.message = message;
    }
}