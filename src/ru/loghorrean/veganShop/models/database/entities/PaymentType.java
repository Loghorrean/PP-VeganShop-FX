package ru.loghorrean.veganShop.models.database.entities;

import java.util.HashSet;
import java.util.Set;

public class PaymentType extends DatabaseEntity {
    private String name;
    private final Set<Order> orders;

    public PaymentType(int id, String name) {
        super(id);
        this.name = name;
        orders = new HashSet<>();
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

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public Set<Order> getOrders() {
        return orders;
    }
}
