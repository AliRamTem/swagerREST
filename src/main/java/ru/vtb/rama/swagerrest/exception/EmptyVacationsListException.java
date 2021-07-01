package ru.vtb.rama.swagerrest.exception;

public class EmptyVacationsListException  extends BaseException{

    public EmptyVacationsListException() {
        super("Expected \"vacations\" to be not empty");
    }
}