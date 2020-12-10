package ru.loghorrean.veganShop.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ru.loghorrean.veganShop.controllers.AdminController;

import java.io.IOException;

public class AdminMenuController extends AdminController {
    @FXML
    private AnchorPane menuAnchorPane;

    @FXML
    private Button usersButton;

    @FXML
    private Button productsButton;

    @FXML
    private Button dishesButton;

    @FXML
    private Button categoriesButton;

    @FXML
    private Button templatesButton;

    public void initialize() {
        Accordion userMenu = getUserMenu();
        AnchorPane.setTopAnchor(userMenu, 10.0);
        AnchorPane.setRightAnchor(userMenu, 10.0);
        menuAnchorPane.getChildren().add(userMenu);
    }

    @FXML
    public void goToUsersPage(ActionEvent event) {
        try {
            redirect(event, "AdminUsers");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToProductsPage(ActionEvent event) {
        try {
            redirect(event, "AdminProducts");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToDishesPage(ActionEvent event) {
        try {
            redirect(event, "AdminDishes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToCategoriesPage(ActionEvent event) {
        try {
            redirect(event, "AdminCategories");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToTemplatesPage(ActionEvent event) {
        try {
            redirect(event, "AdminTemplates");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
