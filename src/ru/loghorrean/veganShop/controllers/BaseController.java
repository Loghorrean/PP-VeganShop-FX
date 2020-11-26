package ru.loghorrean.veganShop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

abstract public class BaseController {
    protected void setMistake(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Внимание!");
        alert.setHeaderText("Ошибка:");
        alert.setContentText(message);
        alert.showAndWait();
    }

    protected void setSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успешно");
        alert.setContentText(message);
        alert.showAndWait();
    }

    protected void redirect(ActionEvent event, String scene) throws IOException {
        Parent tableView = FXMLLoader.load(getClass().getResource("/ru/loghorrean/veganShop/views/" + scene + ".fxml"));
        Scene tableViewScene = new Scene(tableView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    abstract protected void initialize();
}
