package ru.loghorrean.veganShop.controllers.client.dishCon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ru.loghorrean.veganShop.Cart;
import ru.loghorrean.veganShop.controllers.ClientController;
import ru.loghorrean.veganShop.models.GeneralDishesData;
import ru.loghorrean.veganShop.models.database.entities.GeneralDish;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.io.IOException;
import java.util.Optional;

public class AddGeneralToOrderController extends ClientController {
    private GeneralDishesData model;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private GridPane dishGrid;

    @Override
    public void initialize() {
        Button button = new Button("Назад в меню");
        button.setOnAction(event -> {
            try {
                redirect(event, "orderScreens/FillOrder");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        mainBorderPane.setBottom(button);
        model = GeneralDishesData.getInstance();
        mainBorderPane.setRight(getUserMenu());
        int i = 0;
        for (GeneralDish dish: model.getDishes()) {
            setGridRow(dish, i);
            i++;
        }
    }

    private void setGridRow(GeneralDish dish, int row) {
        dishGrid.add(new Label(dish.getName()), 0, row);
        Button infoButton = new Button("Информация о блюде");
        infoButton.setOnAction(event -> openInfoDialog(event, dish));
        dishGrid.add(infoButton, 1, row);
        Button addButton = new Button("Добавить блюдо");
        addButton.setOnAction(event -> openAddingDialog(event, dish));
        dishGrid.add(addButton, 2, row);
        if (Cart.getInstance().getGeneralFromCart().containsKey(dish)) {
            addButton.setDisable(true);
            Button updateButton = new Button("Изменить количество в корзине");
            updateButton.setOnAction(event -> openUpdateDialog(event, dish));
            dishGrid.add(updateButton, 3, row);
        }
    }

    private void openInfoDialog(ActionEvent event, GeneralDish dish) {
        try {
            redirectWithSmth(event, "dishesScreens/DishComposition", dish);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openAddingDialog(ActionEvent event, GeneralDish dish) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("GeneralToOrderDialog")
                                .createDialog("Добавьте блюдо", mainBorderPane)
                                .addButtons(ButtonType.OK, ButtonType.CANCEL)
                                .addController()
                                .passObject(dish)
                                .fillDialog()
                                .addValidationToButton(ButtonType.OK)
                                .onSuccess("addGeneralToCart")
                                .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                redirect(event, "dishesScreens/AddGeneralToOrder");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openUpdateDialog(ActionEvent event, GeneralDish dish) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("GeneralToOrderDialog")
                                .createDialog("Измените количество в корзине", mainBorderPane)
                                .addButtons(ButtonType.OK, ButtonType.CANCEL)
                                .addController()
                                .passObject(dish)
                                .fillDialog()
                                .addValidationToButton(ButtonType.OK)
                                .onSuccess("updateGeneralInCart")
                                .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                redirect(event, "dishesScreens/AddGeneralToOrder");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
