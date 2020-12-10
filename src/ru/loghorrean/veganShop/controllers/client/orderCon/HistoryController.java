package ru.loghorrean.veganShop.controllers.client.orderCon;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import ru.loghorrean.veganShop.CurrentUser;
import ru.loghorrean.veganShop.controllers.ClientController;
import ru.loghorrean.veganShop.models.CustomInOrderData;
import ru.loghorrean.veganShop.models.GeneralInOrderData;
import ru.loghorrean.veganShop.models.database.entities.*;

import java.io.IOException;

public class HistoryController extends ClientController {
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ListView<Order> mainListView;

    @FXML
    private VBox mainVBox;

    @FXML
    private TextArea mainTextArea;

    @FXML
    private Button repeatOrder;

    @Override
    public void initialize() {
        mainBorderPane.setRight(getUserMenu());
        Button backButton = new Button("Назад в меню");
        backButton.setOnAction(event -> {
            try {
                redirect(event, "orderScreens/Menu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        mainBorderPane.setBottom(backButton);

        mainListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                Order chosenOrder = mainListView.getSelectionModel().getSelectedItem();
                mainTextArea.setText(mainTextArea.getText() + "Основные блюда в заказе " + '\n');
                if (chosenOrder.getGeneralDishesInOrder().isEmpty()) {
                    mainTextArea.setText(mainTextArea.getText() + "Отсутствуют " + '\n');
                } else {
                    for (GeneralDish dish: chosenOrder.getGeneralDishesInOrder()) {
                        GeneralDishInOrder link = GeneralInOrderData.getInstance().getLink(dish, chosenOrder);
                        mainTextArea.setText(mainTextArea.getText() + "Блюдо " + dish.getName() + " в количестве " + link.getAmount() + " блюда" + '\n');
                    }
                }
                mainTextArea.setText(mainTextArea.getText() + "Кастомные блюда в заказе: " + '\n');
                if (chosenOrder.getCustomDishesInOrder().isEmpty()) {
                    mainTextArea.setText(mainTextArea.getText() + "Отсутствуют" + '\n');
                } else {
                    for (CustomDish dish: chosenOrder.getCustomDishesInOrder()) {
                        CustomDishInOrder link = CustomInOrderData.getInstance().getLink(dish, chosenOrder);
                        mainTextArea.setText(mainTextArea.getText() + "Блюдо " + dish.getName() + "в количестве " + link.getAmount() + '\n');
                    }
                }
                mainTextArea.setText(mainTextArea.getText() + "Цена заказа: " + chosenOrder.getPrice() + '\n');
            }
        });

        mainListView.setItems(FXCollections.observableArrayList(CurrentUser.getInstance().getUser().getOrdersOfUser()));
        mainListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        mainListView.getSelectionModel().selectFirst();

        mainListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Order> call(ListView<Order> orderListView) {
                ListCell<Order> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(Order order, boolean empty) {
                        super.updateItem(order, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText("Заказ номер " + order.getId());
                        }
                    }
                };
                return cell;
            }
        });
    }
}
