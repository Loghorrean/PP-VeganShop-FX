package ru.loghorrean.veganShop;

import ru.loghorrean.veganShop.models.database.entities.UserEntity;

public class CurrentUser {
    private UserEntity currentUser;
    private static CurrentUser instance;

    private CurrentUser(UserEntity user) {
        currentUser = user;
    }

    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser(null);
        }
        return instance;
    }

    public void setUser(UserEntity user) {
        this.currentUser = user;
    }

    public UserEntity getUser() {
        return this.currentUser;
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "currentUser=" + currentUser +
                '}';
    }
}
