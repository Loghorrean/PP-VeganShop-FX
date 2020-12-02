package ru.loghorrean.veganShop.models.database.entities;

import ru.loghorrean.veganShop.exceptions.DatabaseException;

public class GeneralDishInOrder extends DatabaseEntity {
    private GeneralDish dish;
    private Order order;
    private int amount;

    public GeneralDishInOrder(int id, GeneralDish dish, Order order, int amount) {
        super(id);
        this.dish = dish;
        this.order = order;
        this.amount = amount;
    }

    public GeneralDishInOrder(GeneralDish dish, Order order, int amount) {
        this(-1, dish, order, amount);
    }

    public GeneralDish getDish() {
        return dish;
    }

    public void setDish(GeneralDish dish) {
        this.dish = dish;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
