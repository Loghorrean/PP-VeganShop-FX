package ru.loghorrean.veganShop.models.database.entities;

import java.time.LocalDate;

public class Order extends DatabaseEntity {
    private int id;
    private User user;
    private LocalDate orderDate;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
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
        this.city = city;
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
        this.paymentType = paymentType;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
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

    public static class OrderBuilder {
        private Order order;

        public OrderBuilder() {
            order = new Order();
            order.id = -1;
        }

        public OrderBuilder withId(int id) {
            order.id = id;
            return this;
        }

        public OrderBuilder withUser(User user) {
            order.user = user;
            return this;
        }

        public OrderBuilder withDate(LocalDate date) {
            order.orderDate = date;
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
            return this;
        }

        public OrderBuilder withCourier(Courier courier) {
            order.courier = courier;
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
