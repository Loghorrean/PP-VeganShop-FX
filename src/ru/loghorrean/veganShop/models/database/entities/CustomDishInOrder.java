package ru.loghorrean.veganShop.models.database.entities;

public class CustomDishInOrder extends DatabaseEntity {
    private CustomDish dish;
    private Order order;
    private float amount;

    public CustomDishInOrder(int id, CustomDish dish, Order order, float amount) {
        super(id);
        this.dish = dish;
        dish.addOrder(order);
        this.order = order;
        order.addCustomDish(dish);
        this.amount = amount;
    }

    public CustomDishInOrder(CustomDish dish, Order order, float amount) {
        this(-1, dish, order, amount);
    }

    public CustomDish getDish() {
        return dish;
    }

    public void setDish(CustomDish dish) {
        this.dish.removeOrder(order);
        this.dish = dish;
        this.dish.addOrder(order);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order.removeCustomDish(dish);
        this.order = order;
        this.order.addCustomDish(dish);
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void destroyLink() {
        dish.removeOrder(order);
        order.removeCustomDish(dish);
    }
}
