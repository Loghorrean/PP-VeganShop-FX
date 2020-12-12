package ru.loghorrean.veganShop.controllers.client.orderCon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ru.loghorrean.veganShop.Cart;
import ru.loghorrean.veganShop.controllers.ClientController;
import ru.loghorrean.veganShop.models.database.entities.CustomDish;
import ru.loghorrean.veganShop.models.database.entities.GeneralDish;

import java.io.IOException;

public class CartController extends ClientController {
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private GridPane generalGrid;

    @FXML
    private GridPane customGrid;

    @FXML
    private Button orderButton;

    @FXML
    private Label cartStatus;

    private Cart cart;

    private static int start = 1;

    @Override
    public void initialize() {
        Button backButton = new Button("Назад в меню");
        backButton.setOnAction(event -> {
            try {
                redirect(event, "orderScreens/Menu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        mainBorderPane.setRight(getUserMenu());
        mainBorderPane.setBottom(backButton);
        cart = Cart.getInstance();
        if (!cart.getCustomFromCart().isEmpty() || !cart.getGeneralFromCart().isEmpty()) {
            cartStatus.setText(cartStatus.getText() + cart.getNumberOfItems() + " различных блюда");
            orderButton.setVisible(true);
            fillGeneralGrid();
            start = 1;
            fillCustomGrid();
        } else {
            cartStatus.setText("Товары отсутствуют в корзине");
        }
    }

    private void fillGeneralGrid() {
        cart.getGeneralFromCart().forEach((k, v) -> fillGeneralRow(k, v, start));
    }

    private void fillGeneralRow(GeneralDish dish, int numberOfDishes, int row) {
        System.out.println(dish.getName());
        System.out.println(numberOfDishes);
        System.out.println(row);
        generalGrid.add(new Label(dish.getName()), 0, row);
        generalGrid.add(new Label(Integer.toString(numberOfDishes)), 1, row);
        start++;
    }

    private void fillCustomGrid() {
        cart.getCustomFromCart().forEach((k, v) -> fillCustomRow(k, v, start));
    }

    private void fillCustomRow(CustomDish dish, int numberOfDishes, int row) {
        start++;
    }

    @FXML
    protected void makeOrder(ActionEvent event) {

    }
}
