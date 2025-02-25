package ru.loghorrean.veganShop.models.database.entities;

import java.util.HashSet;
import java.util.Set;

public class Order extends DatabaseEntity {
    private User user;
    private float price;
    private String phone;
    private City city;
    private String street;
    private int house;
    private int flat;
    private String comment;
    private boolean isConfirmed;
    private final Set<GeneralDish> generalDishesInOrder;
    private final Set<CustomDish> customDishesInOrder;

    private Order(int id) {
        super(id);
        generalDishesInOrder = new HashSet<>();
        customDishesInOrder = new HashSet<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user.removeOrder(this);
        this.user = user;
        this.user.addOrder(this);
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city.removeOrder(this);
        this.city = city;
        this.city.addOrder(this);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean getConfirmation() {
        return isConfirmed;
    }

    public void setConfirmation(boolean confirmation) {
        this.isConfirmed = confirmation;
    }

    public void addGeneralDish(GeneralDish generalDish) {
        generalDishesInOrder.add(generalDish);
    }

    public void removeGeneralDish(GeneralDish generalDish) {
        generalDishesInOrder.remove(generalDish);
    }

    public Set<GeneralDish> getGeneralDishesInOrder() {
        return generalDishesInOrder;
    }

    public void addCustomDish(CustomDish customDish) {
        customDishesInOrder.add(customDish);
    }

    public void removeCustomDish(CustomDish customDish) {
        customDishesInOrder.remove(customDish);
    }

    public Set<CustomDish> getCustomDishesInOrder() {
        return customDishesInOrder;
    }

    public void deleteOrder() {
        user.removeOrder(this);
        city.removeOrder(this);
    }

    public static class OrderBuilder {
        private final Order order;

        public OrderBuilder() {
            order = new Order(-1);
        }

        public OrderBuilder withId(int id) {
            order.id = id;
            return this;
        }

        public OrderBuilder withUser(User user) {
            order.user = user;
            user.addOrder(order);
            return this;
        }

        public OrderBuilder withPrice(float price) {
            order.price = price;
            return this;
        }

        public OrderBuilder withPhone(String phone) {
            order.phone = phone;
            return this;
        }

        public OrderBuilder withCity(City city) {
            order.city = city;
            city.addOrder(order);
            return this;
        }

        public OrderBuilder withStreet(String street) {
            order.street = street;
            return this;
        }

        public OrderBuilder withHouse(int house) {
            order.house = house;
            return this;
        }

        public OrderBuilder withFlat(int flat) {
            order.flat = flat;
            return this;
        }

        public OrderBuilder withComment(String comment) {
            order.comment = comment;
            return this;
        }

        public OrderBuilder withConfirmation(boolean confirmation) {
            order.isConfirmed = confirmation;
            return this;
        }

        public Order build() {
            return order;
        }
    }
}
