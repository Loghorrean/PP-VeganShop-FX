package ru.loghorrean.veganShop.models.database.entities;

import ru.loghorrean.veganShop.exceptions.CourierException;
import ru.loghorrean.veganShop.exceptions.DatabaseException;
import ru.loghorrean.veganShop.util.validators.Validator;

public class Courier extends DatabaseEntity {
    private String firstname = "";
    private String lastname = "";
    private String phone = "";
    private String verifyCode = "";

    public Courier(int id, String firstname, String lastname, String phone, String verifyCode) throws DatabaseException {
        super(id);
        setFirstname(firstname);
        setLastname(lastname);
        setPhone(phone);
        setVerifyCode(verifyCode);
    }

    public Courier(String firstname, String lastname, String phone, String verifyCode) throws DatabaseException {
        this(-1, firstname, lastname, phone, verifyCode);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) throws CourierException {
        if (firstname.length() < 2 || firstname.length() > 20) {
            throw new CourierException("Name should have between 2 and 20 symbols");
        }
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) throws CourierException {
        if (lastname.length() < 2 || lastname.length() > 20) {
            throw new CourierException("Last name should have between 2 and 20 symbols");
        }
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws CourierException {
        if (!Validator.validatePhone(phone)) {
            throw new CourierException("Неправильный формат номера телефона");
        }
        this.phone = phone;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) throws CourierException {
        if (verifyCode.length() != 20) {
            throw new CourierException("Длина кода верификации должна быть ровно 20 символов");
        }
        this.verifyCode = verifyCode;
    }
}
