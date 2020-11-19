package ru.loghorrean.veganShop.controllers;

import javafx.scene.control.Alert;

abstract public class BaseController {
    protected void setMistake(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Внимание!");
        alert.setHeaderText("Ошибка:");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
