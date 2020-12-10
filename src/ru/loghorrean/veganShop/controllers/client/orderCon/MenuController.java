package ru.loghorrean.veganShop.controllers.client.orderCon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.UserController;
import ru.loghorrean.veganShop.models.OrdersData;
import ru.loghorrean.veganShop.models.UsersData;
import ru.loghorrean.veganShop.models.database.entities.User;

import java.io.IOException;
import java.sql.SQLException;

public class MenuController extends UserController {
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
    }

    @FXML
    public void createOrder(ActionEvent event) {
        try {
            redirect(event, "mainScreens/FillOrder");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToCart(ActionEvent event) {
        try {
            redirect(event, "mainScreens/Cart");
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
            redirect(event, "mainScreens/History");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
