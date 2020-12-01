package ru.loghorrean.veganShop.exceptions;

public class OrderException extends DatabaseException {
    public OrderException(String message) {
        super(message);
    }
}
