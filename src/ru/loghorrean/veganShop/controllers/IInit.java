package ru.loghorrean.veganShop.controllers;

import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;

public interface IInit {
    public void initData(DatabaseEntity object);
}
