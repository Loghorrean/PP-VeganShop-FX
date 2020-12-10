package ru.loghorrean.veganShop.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;
import ru.loghorrean.veganShop.models.database.entities.Product;
import ru.loghorrean.veganShop.models.database.entities.ProductCategory;

import java.util.Set;

public class ProductsInCategoryController extends DialogController implements IInit {
    @FXML
    private Label testLabel;

    @Override
    public void initData(DatabaseEntity object) {
        ProductCategory chosenCategory = (ProductCategory) object;
        Set<Product> productsToShow = chosenCategory.getProductsOfCategory();
        System.out.println(productsToShow);
        for (Product product: productsToShow) {
            testLabel.setText(testLabel.getText() + product.getName() + "\r\n");
        }
    }

    @Override
    public void initialize() {

    }

    @Override
    public boolean checkFields() {
        return false;
    }
}
