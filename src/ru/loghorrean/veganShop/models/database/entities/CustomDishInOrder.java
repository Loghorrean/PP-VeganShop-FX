package ru.loghorrean.veganShop.models.database.entities;

public class CustomDishInOrder extends DatabaseEntity {
    private int id;
    private CustomDish dish;
    private Order order;
    private int amount;

    public CustomDishInOrder(int id, CustomDish dish, Order order, int amount) {
        this.id = id;
        this.dish = dish;
        this.order = order;
        this.amount = amount;
    }

    public CustomDishInOrder(CustomDish dish, Order order, int amount) {
        this(-1, dish, order, amount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomDish getDish() {
        return dish;
    }

    public void setDish(CustomDish dish) {
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
