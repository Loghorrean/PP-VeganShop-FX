package ru.loghorrean.veganShop.exceptions;

public class CustomDishException extends DatabaseException {
    public CustomDishException(String message) {
        super(message);
    }
}
