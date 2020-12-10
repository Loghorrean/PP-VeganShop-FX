package ru.loghorrean.veganShop.controllers.client.orderCon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ru.loghorrean.veganShop.controllers.ClientController;

import java.io.IOException;

public class FillOrderController extends ClientController {
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Button backButton;

    @Override
    public void initialize() {
        Button cartButton = new Button("Перейти в корзину");
        cartButton.setOnAction(event -> {
            try {
                redirect(event, "mainScreens/Cart");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        VBox vBox = new VBox(10, getUserMenu(), cartButton);
        mainBorderPane.setRight(vBox);
    }

    @FXML
    public void backToMenu(ActionEvent event) {
        try {
            redirect(event, "mainScreens/Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToGeneral(ActionEvent event) {
        try {
            redirect(event, "dishesScreens/AddGeneralToOrder");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToCustom(ActionEvent event) {
        try {
            redirect(event, "dishesScreens/AddCustomToOrder");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
