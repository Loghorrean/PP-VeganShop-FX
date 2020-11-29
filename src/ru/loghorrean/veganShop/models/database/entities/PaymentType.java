package ru.loghorrean.veganShop.models.database.entities;

public class PaymentType extends DatabaseEntity {
    private int id;
    private String name;

    public PaymentType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PaymentType(String name) {
        this(-1, name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
