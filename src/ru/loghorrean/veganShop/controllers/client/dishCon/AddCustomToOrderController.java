package ru.loghorrean.veganShop.controllers.client.dishCon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import ru.loghorrean.veganShop.CurrentCustomDish;
import ru.loghorrean.veganShop.controllers.ClientController;

import java.io.IOException;

public class AddCustomToOrderController extends ClientController {
    @FXML
    private BorderPane mainBorderPane;

    @Override
    public void initialize() {
        Button backButton = new Button("Назад");
        backButton.setOnAction(event -> {
            try {
                redirect(event, "orderScreens/FillOrder");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        mainBorderPane.setBottom(backButton);
        mainBorderPane.setRight(getUserMenu());
    }

    @FXML
    protected void createNewCustom(ActionEvent event) {
        try {
            redirectWithSmth(event, "dishesScreens/NewCustomDish", new CurrentCustomDish());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void openListOfCustom(ActionEvent event) {
        try {
            redirect(event, "dishesScreens/CustomDishList");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
