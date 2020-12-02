package ru.loghorrean.veganShop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

import java.io.IOException;

abstract public class AdminPanelController extends UserController {
    @FXML
    protected Button backButton;

    protected MenuBar getAdminMenu(String stringForAdding, Pane paneForDialog) {
        MenuBar adminMenu = new MenuBar();
        Menu menu = new Menu();
        menu.setText("Меню");
        MenuItem addEntity = new MenuItem(stringForAdding);
        addEntity.setOnAction(event -> {
            openAddDialog();
        });
        MenuItem info = new MenuItem("Как пользоваться");
        info.setOnAction(event -> {
            openInfoDialog(paneForDialog);
        });
        menu.getItems().setAll(addEntity, info);
        adminMenu.getMenus().add(menu);
        return adminMenu;
    }

    @FXML
    public abstract void openInfoDialog(Pane paneForDialog);

    @FXML
    public abstract void openAddDialog();

    @FXML
    public void backToTheMenu(ActionEvent event) {
        try {
            redirect(event, "admin/AdminMenuWindow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
