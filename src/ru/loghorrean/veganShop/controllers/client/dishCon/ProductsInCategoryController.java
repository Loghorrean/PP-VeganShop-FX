package ru.loghorrean.veganShop.controllers.client.dishCon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ru.loghorrean.veganShop.controllers.ClientController;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;
import ru.loghorrean.veganShop.models.database.entities.Product;
import ru.loghorrean.veganShop.models.database.entities.ProductCategory;

import java.io.IOException;
import java.util.Set;

public class ProductsInCategoryController extends ClientController implements IInit {
    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private GridPane productGrid;

    @FXML
    private Label catName;

    @FXML
    private Button backButton;

    @Override
    public void initData(Object object) {
        ProductCategory category = (ProductCategory) object;
        catName.setText(category.getName());
        Set<Product> products = category.getProductsOfCategory();
        int i = 0;
        for (Product product: products) {
            productGrid.add(new Label(product.getName()), 0, i);
            Button button = new Button();
            button.setText("Подробнее");
            button.setOnAction(event -> {
                try {
                    redirectWithSmth(event, "mainScreens/Product", product);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            productGrid.add(button, 1, i);
            i++;
        }
    }

    @Override
    public void initialize() {
        Accordion userMenu = getUserMenu();
        AnchorPane.setTopAnchor(userMenu, 10.0);
        AnchorPane.setRightAnchor(userMenu, 10.0);
        mainAnchorPane.getChildren().add(userMenu);
    }

    @FXML
    public void goBack(ActionEvent event) {
        try {
            redirect(event, "client/Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
