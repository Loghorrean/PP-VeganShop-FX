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
            //TODO: FINISH THIS
        }
        return instance;
    }
}
