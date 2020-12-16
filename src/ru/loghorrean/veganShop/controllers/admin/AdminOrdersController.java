package ru.loghorrean.veganShop.controllers.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import ru.loghorrean.veganShop.controllers.AdminControllerWithGrid;
import ru.loghorrean.veganShop.models.OrdersData;
import ru.loghorrean.veganShop.models.ProductsInGeneralDishesData;
import ru.loghorrean.veganShop.models.database.entities.GeneralDish;
import ru.loghorrean.veganShop.models.database.entities.Order;
import ru.loghorrean.veganShop.models.database.entities.Product;
import ru.loghorrean.veganShop.models.database.entities.ProductInGeneralDish;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AdminOrdersController extends AdminControllerWithGrid {
    private OrdersData model;

    @Override
    public void openAddDialog(ActionEvent event) {

    }

    @Override
    public void initialize() {
        model = OrdersData.getInstance();
        mainBorderPane.setRight(getUserMenu());
        mainBorderPane.setBottom(getBackButton());
        setGrid();
    }

    private void setGrid() {
        List<Order> orders = OrdersData.getInstance().getOrders();
        int i = 1;
        for (Order order: orders) {
            fillGridRow(order, i);
            ++i;
        }
    }

    private void fillGridRow(Order order, int row) {
        mainGridPane.add(new Label(Integer.toString(order.getId())), 0, row);
        mainGridPane.add(new Label(Float.toString(order.getPrice())), 1, row);
        mainGridPane.add(new Label(order.getConfirmation() ? "Да" : "Нет"), 2, row);
        Button confirmButton = new Button("Подтвердить заказ");
        confirmButton.setOnAction(event -> openConfirmDialog(event, order));
        mainGridPane.add(confirmButton, 3, row);
        Button detailsButton = new Button("Детали заказа");
        detailsButton.setOnAction(event -> openDetailsDialog(event, order));
        mainGridPane.add(detailsButton, 4, row);
    }

    private void openConfirmDialog(ActionEvent event, Order order) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText("Подтвердить заказ номер " + order.getId());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                order.setConfirmation(true);
                model.updateOrderInModel(order);
                setSuccess("Заказ подтвержден");
                redirect(event, "AdminOrders");
            } catch (IOException | SQLException e) {
                System.out.println("ERROR WHILE DELETING CATEGORY");
                e.printStackTrace();
            }
        }
    }

    private void openDetailsDialog(ActionEvent event, Order order) {

    }
}
