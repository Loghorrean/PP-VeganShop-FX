package ru.loghorrean.veganShop.controllers;

import javafx.event.ActionEvent;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;

import java.io.IOException;

abstract public class ClientController extends UserController {
    @Override
    protected void redirect(ActionEvent event, String scene) throws IOException {
        super.redirect(event, "client/" + scene);
    }

    @Override
    public void redirectWithSmth(ActionEvent event, String scene, DatabaseEntity object) throws IOException {
        super.redirectWithSmth(event, "client/" + scene, object);
    }
}
