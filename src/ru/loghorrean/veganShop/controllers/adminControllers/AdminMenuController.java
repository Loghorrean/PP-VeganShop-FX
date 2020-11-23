package ru.loghorrean.veganShop.controllers.adminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ru.loghorrean.veganShop.controllers.BaseController;

import java.io.IOException;

public class AdminMenuController extends BaseController {
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

    @FXML
    private Button reviewsButton;

    public void initialize() {
        Accordion userMenu = getUserMenu();
        AnchorPane.setTopAnchor(userMenu, 10.0);
        AnchorPane.setRightAnchor(userMenu, 10.0);
        menuAnchorPane.getChildren().add(userMenu);
    }

    @FXML
    public void goToUsersPage(ActionEvent event) {
        try {
            redirect(event, "admin/AdminUsersWindow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToProductsPage(ActionEvent event) {
        try {
            redirect(event, "admin/AdminProductsWindow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToDishesPage(ActionEvent event) {
        try {
            redirect(event, "admin/AdminDishesWindow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToCategoriesPage(ActionEvent event) {
        try {
            redirect(event, "admin/AdminCategoriesWindow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToTemplatesPage(ActionEvent event) {
        try {
            redirect(event, "admin/AdminTemplatesWindow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToReviewsPage(ActionEvent event) {
        try {
            redirect(event, "admin/AdminReviewsWindow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
