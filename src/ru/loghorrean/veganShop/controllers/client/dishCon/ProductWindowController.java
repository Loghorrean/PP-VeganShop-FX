package ru.loghorrean.veganShop.controllers.client.dishCon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.controllers.UserController;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;
import ru.loghorrean.veganShop.models.database.entities.Product;

import java.io.IOException;

public class ProductWindowController extends UserController implements IInit {
    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Button backButton;

    private Product product;

    @Override
    public void initialize() {
        Accordion userMenu = getUserMenu();
        AnchorPane.setTopAnchor(userMenu, 10.0);
        AnchorPane.setRightAnchor(userMenu, 10.0);
        mainAnchorPane.getChildren().add(userMenu);
    }

    @Override
    public void initData(DatabaseEntity object) {
        product = (Product) object;
        System.out.println(product.getName());
    }

    @FXML
    public void goBack(ActionEvent event) {
        try {
            redirect(event, "admin/AdminMenu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
