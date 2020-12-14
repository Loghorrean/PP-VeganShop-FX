package ru.loghorrean.veganShop.controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

abstract public class AdminController extends UserController {
    @Override
    protected void redirect(ActionEvent event, String scene) throws IOException {
        super.redirect(event, "admin/" + scene);
    }

    @Override
    public void redirectWithSmth(ActionEvent event, String scene, Object object) throws IOException {
        super.redirectWithSmth(event, "admin/" + scene, object);
    }
}
