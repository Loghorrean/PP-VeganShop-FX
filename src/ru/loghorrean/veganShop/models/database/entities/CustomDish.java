package ru.loghorrean.veganShop.models.database.entities;

import java.util.HashSet;
import java.util.Set;

public class CustomDish extends DatabaseEntity {
    private DishTemplate template;
    private String name;
    private String recipe;
    private User userCreated;
    private final Set<Product> products;
    private final Set<Order> ordersWithDish;

    public CustomDish(int id, DishTemplate template, String name, String recipe, User userCreated) {
        super(id);
        this.template = template;
        this.name = name;
        this.recipe = recipe;
        this.userCreated = userCreated;
        products = new HashSet<>();
        ordersWithDish = new HashSet<>();
    }

    public CustomDish(DishTemplate template, String name, String recipe, User userCreated) {
        this(-1, template, name, recipe, userCreated);
    }

    public DishTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DishTemplate template) {
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public User getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void addOrder(Order order) {
        ordersWithDish.add(order);
    }

    public void removeOrder(Order order) {
        ordersWithDish.remove(order);
    }

    public Set<Order> getOrdersWithDish() {
        return ordersWithDish;
    }
}
