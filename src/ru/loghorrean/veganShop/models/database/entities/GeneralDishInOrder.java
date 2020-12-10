package ru.loghorrean.veganShop.models.database.entities;

public class GeneralDishInOrder extends DatabaseEntity {
    private GeneralDish dish;
    private Order order;
    private int amount;

    public GeneralDishInOrder(int id, GeneralDish dish, Order order, int amount) {
        super(id);
        this.dish = dish;
        dish.addOrder(order);
        this.order = order;
        order.addGeneralDish(dish);
        this.amount = amount;
    }

    public GeneralDishInOrder(GeneralDish dish, Order order, int amount) {
        this(-1, dish, order, amount);
    }

    public GeneralDish getDish() {
        return dish;
    }

    public void setDish(GeneralDish dish) {
        this.dish.removeOrder(order);
        this.dish = dish;
        this.dish.addOrder(order);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order.removeGeneralDish(dish);
        this.order = order;
        this.order.addGeneralDish(dish);
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void destroyLink() {
        dish.removeOrder(order);
        order.removeGeneralDish(dish);
    }
}
