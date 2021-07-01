package ru.vtb.rama.swagerrest.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler({NoSuchUserException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleUserNotFoundException(NoSuchUserException ex){
        log.error("User not found exception: ", ex);
        return ex.getMessage();
    }

    @ExceptionHandler({EmptyVacationsListException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleUserNotFoundException(EmptyVacationsListException ex){
        log.error("Empty vacations list: ", ex);
        return ex.getMessage();
    }
}
