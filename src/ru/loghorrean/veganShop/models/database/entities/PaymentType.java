package ru.loghorrean.veganShop.models.database.entities;

import ru.loghorrean.veganShop.exceptions.DatabaseException;

public class PaymentType extends DatabaseEntity {
    private String name;

    public PaymentType(int id, String name) throws DatabaseException {
        super(id);
        this.name = name;
    }

    public PaymentType(String name) throws DatabaseException {
        this(-1, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
