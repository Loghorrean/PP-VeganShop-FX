package ru.loghorrean.veganShop.controllers.client.dishCon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import ru.loghorrean.veganShop.controllers.ClientController;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.database.entities.CustomDish;
import ru.loghorrean.veganShop.models.database.entities.Product;

import java.io.IOException;

public class CustomDishCompositionController extends ClientController implements IInit {
    @FXML
    private Button backButton;

    @FXML
    private Label dishName;

    @FXML
    private TextArea dishRecipe;

    @FXML
    private ListView<Product> dishComposition;

    private CustomDish dish;

    @Override
    public void initialize() {

    }

    @Override
    public void initData(Object object) {
        dish = (CustomDish) object;
    }

    @FXML
    protected void goBack(ActionEvent event) {
        try {
            redirect(event, "dishesScreens/CustomDishList");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
