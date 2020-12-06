package ru.loghorrean.veganShop.models.database.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Order extends DatabaseEntity {
    private User user;
    private int price;
    private String phone;
    private City city;
    private String street;
    private int house;
    private int flat;
    private String comment;
    private PaymentType paymentType;
    private Courier courier;
    private int courierRating;
    private int foodRating;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType.removeOrder(this);
        this.paymentType = paymentType;
        this.paymentType.addOrder(this);
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier.removeOrder(this);
        this.courier = courier;
        this.courier.addOrder(this);
    }

    public int getCourierRating() {
        return courierRating;
    }

    public void setCourierRating(int courierRating) {
        this.courierRating = courierRating;
    }

    public int getFoodRating() {
        return foodRating;
    }

    public void setFoodRating(int foodRating) {
        this.foodRating = foodRating;
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
        paymentType.removeOrder(this);
        courier.removeOrder(this);
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

        public OrderBuilder withPrice(int price) {
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

        public OrderBuilder withPaymentType(PaymentType paymentType) {
            order.paymentType = paymentType;
            paymentType.addOrder(order);
            return this;
        }

        public OrderBuilder withCourier(Courier courier) {
            order.courier = courier;
            courier.addOrder(order);
            return this;
        }

        public OrderBuilder withCourierRating(int rating) {
            order.courierRating = rating;
            return this;
        }

        public OrderBuilder withFoodRating(int rating) {
            order.foodRating = rating;
            return this;
        }
    }
}
