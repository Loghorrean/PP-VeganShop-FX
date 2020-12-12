package ru.loghorrean.veganShop.models.database.entities;

import java.util.HashSet;
import java.util.Set;

public class GeneralDish extends DatabaseEntity {
    private String name;
    private String description;
    private int timeToCook;
    private int prodCosts;
    private final Set<Product> productsInDish;
    private final Set<Order> ordersWithDish;

    public GeneralDish(int id, String name, String description, int timeToCook, int prodCosts) {
        super(id);
        this.name = name;
        this.description = description;
        this.timeToCook = timeToCook;
        this.prodCosts = prodCosts;
        productsInDish = new HashSet<>();
        ordersWithDish = new HashSet<>();
    }

    public GeneralDish(String name, String description, int timeToCook, int prodCosts) {
        this(-1, name, description, timeToCook, prodCosts);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimeToCook() {
        return timeToCook;
    }

    public void setTimeToCook(int timeToCook) {
        this.timeToCook = timeToCook;
    }

    public int getProdCosts() {
        return prodCosts;
    }

    public void setProdCosts(int prodCosts) {
        this.prodCosts = prodCosts;
    }

    public void addProduct(Product product) {
        productsInDish.add(product);
    }

    public void removeProduct(Product product) {
        productsInDish.remove(product);
    }

    public Set<Product> getProductsInDish() {
        return productsInDish;
    }

    public void addOrder(Order order) {
        ordersWithDish.add(order);
    }

    public void removeOrder(Order order) {
        ordersWithDish.remove(order);
    }

    public Set<Order> getDishInOrders() {
        return ordersWithDish;
    }
}
