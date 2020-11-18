package ru.loghorrean.veganShop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LogoController {
    @FXML
    private Button button;

    /**
     * Scene switcher(From the logo scene to the main menu)
     */
    @FXML
    public void goToMainMenu(ActionEvent event) throws IOException {
        Parent tableView = FXMLLoader.load(getClass().getResource("/ru/loghorrean/veganShop/views/MainWindow.fxml"));
        Scene tableViewScene = new Scene(tableView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //получаем сорс, кастим к ноду, получаем сцену и окно
        window.setScene(tableViewScene);
        window.show();
    }
}
