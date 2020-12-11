package ru.loghorrean.veganShop.controllers.client.dishCon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import ru.loghorrean.veganShop.controllers.ClientController;
import ru.loghorrean.veganShop.models.GeneralDishesData;
import ru.loghorrean.veganShop.models.database.entities.GeneralDish;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.io.IOException;
import java.util.List;

public class AddGeneralToOrderController extends ClientController {
    private GeneralDishesData model;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private GridPane dishGrid;

    @Override
    public void initialize() {
        model = GeneralDishesData.getInstance();
        mainBorderPane.setRight(getUserMenu());
        List<GeneralDish> dishes = model.getDishes();
        int i = 0;
        for (GeneralDish dish: dishes) {
            setGridRow(dish, i);
            i++;
        }
    }

    private void setGridRow(GeneralDish dish, int row) {
        dishGrid.add(new Label(dish.getName()), 0, row);
        Button infoButton = new Button("Информация о блюде");
        infoButton.setOnAction(event -> {
            openInfoDialog(event, dish);
        });
        dishGrid.add(infoButton, 1, row);
    }

    private void openInfoDialog(ActionEvent event, GeneralDish dish) {
        try {
            redirectWithSmth(event, "dishesScreens/DishComposition", dish);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
