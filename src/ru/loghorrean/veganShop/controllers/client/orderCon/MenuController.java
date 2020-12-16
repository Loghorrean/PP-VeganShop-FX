package ru.loghorrean.veganShop.controllers.client.orderCon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.ClientController;
import ru.loghorrean.veganShop.models.*;
import ru.loghorrean.veganShop.models.database.entities.CategoriesForTemplate;
import ru.loghorrean.veganShop.models.database.entities.Product;

import java.io.IOException;


public class MenuController extends ClientController {
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Button orderButton;

    @FXML
    private Button cartButton;

    @FXML
    private Button historyButton;

    public void initialize() {
        mainBorderPane.setRight(getUserMenu());
        OrdersData.getInstance();
        CategoriesData.getInstance();
        TemplatesData.getInstance();
        CategoriesForTemplatesData.getInstance();
        ProductsData.getInstance();
    }

    @FXML
    public void createOrder(ActionEvent event) {
        try {
            redirect(event, "orderScreens/FillOrder");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToCart(ActionEvent event) {
        try {
            redirect(event, "orderScreens/Cart");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openHistory(ActionEvent event) {
        if (CurrentUser.getInstance().getUser().getOrdersOfUser().isEmpty()) {
            setMistake("У вас пока нету созданных заказов");
            return;
        }
        try {
            redirect(event, "orderScreens/History");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
