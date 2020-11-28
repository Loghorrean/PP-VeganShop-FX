package ru.loghorrean.veganShop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.BaseController;
import ru.loghorrean.veganShop.models.CategoriesData;

import java.io.IOException;

abstract public class UserController extends BaseController {
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

    protected Button getLogoutButton() {
        Button logoutButton = new Button();
        logoutButton.setText("Разлогиниться");
        logoutButton.setMinWidth(144);
        logoutButton.setMinHeight(48);
        logoutButton.setOnAction(actionEvent -> {
            CurrentUser.getInstance().setUser(null);
            try {
                redirect(actionEvent, "MainWindow");
                CategoriesData.unsetModel();
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

//    public void redirectWithSmth(ActionEvent event, String path, Object object) {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource());
//    }
}
