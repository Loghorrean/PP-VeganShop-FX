package ru.loghorrean.veganShop.controllers;

import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;

public interface IInit {
    void initData(DatabaseEntity object);
}
