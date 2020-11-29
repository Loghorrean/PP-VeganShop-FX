package ru.loghorrean.veganShop.models.database.entities;

public class Courier extends DatabaseEntity {
    private int id;
    private String firstname;
    private String lastname;
    private String phone;
    private String verifyCode;

    public Courier(int id, String firstname, String lastname, String phone, String verifyCode) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.verifyCode = verifyCode;
    }

    public Courier(String firstname, String lastname, String phone, String verifyCode) {
        this(-1, firstname, lastname, phone, verifyCode);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
