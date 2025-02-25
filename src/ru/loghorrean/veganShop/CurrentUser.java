package ru.loghorrean.veganShop;

import ru.loghorrean.veganShop.models.database.entities.User;

public class CurrentUser {
    private User currentUser;
    private static CurrentUser instance;

    private CurrentUser(User user) {
        currentUser = user;
    }

    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser(null);
        }
        return instance;
    }

    public void setUser(User user) {
        this.currentUser = user;
    }

    public User getUser() {
        return this.currentUser;
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "currentUser=" + currentUser +
                '}';
    }
}
