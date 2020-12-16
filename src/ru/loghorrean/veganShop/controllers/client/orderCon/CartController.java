package ru.loghorrean.veganShop.controllers.client.orderCon;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ru.loghorrean.veganShop.Cart;
import ru.loghorrean.veganShop.controllers.ClientController;
import ru.loghorrean.veganShop.models.database.entities.CustomDish;
import ru.loghorrean.veganShop.models.database.entities.GeneralDish;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

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
        generalGrid.add(new Label(dish.getName()), 0, row);
        generalGrid.add(new Label("в количестве " + numberOfDishes), 1, row);
        Button deleteButton = new Button("Удалить из корзины");
        deleteButton.setOnAction(event -> openDeleteGeneralDishDialog(event, dish));
        generalGrid.add(deleteButton, 2, row);
        start++;
    }

    private void fillCustomGrid() {
        cart.getCustomFromCart().forEach((k, v) -> fillCustomRow(k, v, start));
    }

    private void fillCustomRow(CustomDish dish, int numberOfDishes, int row) {
        customGrid.add(new Label(dish.getName()), 0, row);
        customGrid.add(new Label("в количестве " + numberOfDishes), 1, row);
        Button deleteButton = new Button("Удалить из корзины");
        deleteButton.setOnAction(event -> openDeleteCustomDishDialog(event, dish));
        customGrid.add(deleteButton, 2, row);
        start++;
    }

    private void openDeleteGeneralDishDialog(ActionEvent event, GeneralDish dish) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Удалить блюдо из корзины");
        alert.setHeaderText("Удалить " + dish.getName());
        alert.setContentText("Вы уверены? Нажмите OK для подветрждения, или CANCEL для отмены");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            cart.deleteGeneralFromCart(dish);
            setSuccess("Блюдо удалено из корзины");
            try {
                redirect(event, "orderScreens/Cart");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openDeleteCustomDishDialog(ActionEvent event, CustomDish dish) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Удалить блюдо из корзины");
        alert.setHeaderText("Удалить " + dish.getName());
        alert.setContentText("Вы уверены? Нажмите OK для подветрждения, или CANCEL для отмены");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            cart.deleteCustomFromCart(dish);
            setSuccess("Кастомное блюдо удалено из корзины");
            try {
                redirect(event, "orderScreens/CartWindow");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    protected void makeOrder(ActionEvent event) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("OrderConfirmationDialog")
                            .createDialog("Подтвердите заказ", mainBorderPane)
                            .addButtons(ButtonType.OK, ButtonType.CANCEL)
                            .addController()
                            .addValidationToButton(ButtonType.OK)
                            .onSuccess("makeOrder")
                            .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            setSuccess("Заказ создан. Ожидайте подтверждения администратора");
            cart.unsetCart();
            try {
                redirect(event, "orderScreens/MenuWindow");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
