package ru.loghorrean.veganShop.models.database.entities;

import java.util.HashSet;
import java.util.Set;

public class City extends DatabaseEntity {
    private String name;
    private final Set<User> usersWithCity;
    private final Set<Order> ordersWithCity;

    public City(int id, String name) {
        super(id);
        this.name = name;
        usersWithCity = new HashSet<>();
        ordersWithCity = new HashSet<>();
    }

    public City(String name) {
        this(-1, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addUser(User user) {
        usersWithCity.add(user);
    }

    public void removeUser(User user) {
        usersWithCity.remove(user);
    }

    public Set<User> getUsersWithCity() {
        return usersWithCity;
    }

    public void addOrder(Order order) {
        ordersWithCity.add(order);
    }

    public void removeOrder(Order order) {
        ordersWithCity.remove(order);
    }

    public Set<Order> getOrdersWithCity() {
        return ordersWithCity;
    }

    @Override
    public String toString() {
        return name;
    }
}
