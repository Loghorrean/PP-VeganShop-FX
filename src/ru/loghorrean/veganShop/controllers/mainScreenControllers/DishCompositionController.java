package ru.loghorrean.veganShop.controllers.mainScreenControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.controllers.UserController;
import ru.loghorrean.veganShop.models.ProductsInGeneralDishesData;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;
import ru.loghorrean.veganShop.models.database.entities.GeneralDish;
import ru.loghorrean.veganShop.models.database.entities.Product;
import ru.loghorrean.veganShop.models.database.entities.ProductInGeneralDish;

import java.sql.SQLException;

public class DishCompositionController extends UserController implements IInit {
    private GeneralDish dish;

    private ProductsInGeneralDishesData model;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Label dishName;

    @FXML
    private TextArea dishDesc;

    @FXML
    private Label compositionLabel;

    @FXML
    private GridPane dishComposition;

    @Override
    public void initialize() {
        Accordion userMenu = getUserMenu();
        AnchorPane.setTopAnchor(userMenu, 10.0);
        AnchorPane.setRightAnchor(userMenu, 10.0);
        mainAnchorPane.getChildren().add(userMenu);
        model = ProductsInGeneralDishesData.getInstance();
    }

    @Override
    public void initData(DatabaseEntity object) {
        dish = (GeneralDish) object;
        System.out.println(dish.getProductsInDish());
        dishName.setText(dish.getName());
        dishDesc.setText(dish.getDescription());
        compositionLabel.setText(compositionLabel.getText() + dish.getName());
        initComposition();
    }

    private void initComposition() {
        int i = 0;
        for (Product product: dish.getProductsInDish()) {
            fillGridRow(i, model.getLink(product, dish));
            i++;
        }
    }

    private void fillGridRow(int row, ProductInGeneralDish link) {
        dishComposition.add(new Label(link.getProduct().getName()), 0, row);
        dishComposition.add(new Label(Float.toString(link.getAmount())), 1, row);
    }
}
