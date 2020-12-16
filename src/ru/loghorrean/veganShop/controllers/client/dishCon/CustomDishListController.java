package ru.loghorrean.veganShop.controllers.client.dishCon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import ru.loghorrean.veganShop.controllers.ClientController;

import java.io.IOException;

public class CustomDishListController extends ClientController {
    @FXML
    private BorderPane mainBorderPane;

    @Override
    public void initialize() {
        mainBorderPane.setRight(getUserMenu());
        Button backButton = new Button("Назад");
        backButton.setOnAction(event -> {
            try {
                redirect(event, "dishesScreens/AddCustomToOrder");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        mainBorderPane.setBottom(backButton);
    }
}
