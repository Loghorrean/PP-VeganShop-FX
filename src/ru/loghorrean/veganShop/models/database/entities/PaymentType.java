package ru.loghorrean.veganShop.models.database.entities;

public class PaymentType extends DatabaseEntity {
    private String name;

    public PaymentType(int id, String name) {
        super(id);
        this.name = name;
    }

    public PaymentType(String name) {
        this(-1, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
