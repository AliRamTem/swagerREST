package ru.vtb.rama.swagerrest.exception;

public class NoSuchUserException extends BaseException {
    public NoSuchUserException(Long id) {
        super("Пользователь c id " + id + " не найден");
    }
}
