package ru.loghorrean.veganShop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.loghorrean.veganShop.CurrentUser;

import java.io.IOException;

abstract public class BaseController {
    protected void setMistake(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Внимание!");
        alert.setHeaderText("Ошибка:");
        alert.setContentText(message);
        alert.showAndWait();
    }

    protected Button getLogoutButton() {
        Button logoutButton = new Button();
        logoutButton.setText("Разлогиниться");
        logoutButton.setMinWidth(144);
        logoutButton.setMinHeight(48);
        logoutButton.setOnAction(actionEvent -> {
            CurrentUser.getInstance().setUser(null);
            try {
                redirect(actionEvent, "MainWindow");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return logoutButton;
    }

    protected Button getProfileButton() {
        Button profileButton = new Button();
        profileButton.setText("Перейти в профиль");
        profileButton.setMinHeight(48);
        profileButton.setMinWidth(144);
        profileButton.setOnAction(actionEvent -> {
            try {
                redirect(actionEvent, "profile/ProfileWindow");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return profileButton;
    }

    protected void redirect(ActionEvent event, String scene) throws IOException {
        Parent tableView = FXMLLoader.load(getClass().getResource("/ru/loghorrean/veganShop/views/" + scene + ".fxml"));
        Scene tableViewScene = new Scene(tableView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    protected Accordion getUserMenu() {
        TitledPane pane = new TitledPane();
        pane.setText(CurrentUser.getInstance().getUser().getUsername());
        VBox content = new VBox();
        content.getChildren().add(getProfileButton());
        content.getChildren().add(getLogoutButton());
        pane.setContent(content);
        Accordion accordion = new Accordion();
        accordion.getPanes().add(pane);
        return accordion;
    }
}
