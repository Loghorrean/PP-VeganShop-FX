package ru.loghorrean.veganShop.exceptions;

public class UserException extends DatabaseException {
    public UserException(String message) {
        super(message);
    }
}
