package ru.loghorrean.veganShop.models.database.entities;

import ru.loghorrean.veganShop.exceptions.CourierException;
import ru.loghorrean.veganShop.exceptions.DatabaseException;
import ru.loghorrean.veganShop.util.validators.Validator;

import java.util.HashSet;
import java.util.Set;

public class Courier extends DatabaseEntity {
    private String firstname;
    private String lastname;
    private String phone;
    private String verifyCode;
    private final Set<Order> orders;

    public Courier(int id, String firstname, String lastname, String phone, String verifyCode) {
        super(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.verifyCode = verifyCode;
        orders = new HashSet<>();
    }

    public Courier(String firstname, String lastname, String phone, String verifyCode) {
        this(-1, firstname, lastname, phone, verifyCode);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public Set<Order> getOrders() {
        return orders;
    }
}
