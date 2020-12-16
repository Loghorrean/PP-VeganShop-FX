package ru.loghorrean.veganShop.controllers.dialog;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.loghorrean.veganShop.Cart;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.models.*;
import ru.loghorrean.veganShop.models.database.entities.*;
import ru.loghorrean.veganShop.util.validators.Validator;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderConfirmationController extends DialogController {
    @FXML
    private Label orderPrice;

    @FXML
    private TextField orderPhone;

    @FXML
    private ComboBox<City> orderCity;

    @FXML
    private TextField orderStreet;

    @FXML
    private TextField orderHouse;

    @FXML
    private TextField orderFlat;

    @FXML
    private TextArea orderComment;

    private OrdersData model;

    private Cart cart;

    @Override
    public void initialize() {
        try {
            cart = Cart.getInstance();
            orderPrice.setText(Float.toString(cart.getCartPrice()));
            orderCity.setItems(FXCollections.observableArrayList(ProfileData.getInstance().getCities()));
            orderCity.getSelectionModel().selectFirst();
            model = new OrdersData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        orderPrice.setText(Float.toString(Cart.getInstance().getCartPrice()));
    }

    @Override
    public boolean checkFields() {
        if (!Validator.validateAllFields(orderPhone.getText(), orderStreet.getText(), orderHouse.getText(), orderFlat.getText())) {
            setMistake("Основные поля не заполнены");
            return false;
        }
        return true;
    }

    @FXML
    protected void fillAddress(ActionEvent event) {
        User user = CurrentUser.getInstance().getUser();
        orderCity.setValue(user.getCity());
        orderPhone.setText(user.getPhone());
        orderStreet.setText(user.getStreet());
        orderHouse.setText(Integer.toString(user.getHouse()));
        orderFlat.setText(Integer.toString(user.getFlat()));
    }

    public void makeOrder() {
        Order newOrder =
                new Order.OrderBuilder()
                    .withUser(CurrentUser.getInstance().getUser())
                    .withPrice(Cart.getInstance().getCartPrice())
                    .withCity(orderCity.getValue())
                    .withStreet(orderStreet.getText())
                    .withHouse(Integer.parseInt(orderHouse.getText()))
                    .withFlat(Integer.parseInt(orderFlat.getText()))
                    .withComment(orderComment.getText())
                    .withConfirmation(false)
                    .withPhone(orderPhone.getText())
                    .build();
        try {
            model.addOrderToModel(newOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Map<GeneralDish, Integer> generalDishesInOrder = cart.getGeneralFromCart();
        generalDishesInOrder.forEach((k, v) -> {
            GeneralDishInOrder link =
                    new GeneralDishInOrder(
                            k,
                            newOrder,
                            v
                    );
            try {
                GeneralInOrderData.getInstance().addLinkToModel(link);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


        Map<CustomDish, Integer> customDishesInOrder = cart.getCustomFromCart();
        customDishesInOrder.forEach((k, v) -> {
            CustomDishInOrder link =
                    new CustomDishInOrder(
                            k,
                            newOrder,
                            v
                    );
            try {
                CustomInOrderData.getInstance().addLinkToModel(link);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
