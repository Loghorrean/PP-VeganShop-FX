package ru.loghorrean.veganShop.util;

public enum Roles {
    Admin(1),
    Customer(2),
    Courier(3);
    private int roleCode;

    Roles(int roleCode) {
        this.roleCode = roleCode;
    }

    public int getRoleCode() {
        return this.roleCode;
    }
}
