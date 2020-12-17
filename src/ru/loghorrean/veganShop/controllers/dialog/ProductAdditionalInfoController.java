package ru.loghorrean.veganShop.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;
import ru.loghorrean.veganShop.models.database.entities.Product;

public class ProductAdditionalInfoController extends DialogController implements IInit, IFill {
    private Product product;

    @FXML
    private Label productName;

    @FXML
    private Label productDesc;

    @FXML
    private Label productPrice;

    @FXML
    private Label numOfCal;

    @FXML
    private Label isAllergic;

    @FXML
    private Label productCat;

    @Override
    public void initialize() {

    }

    @Override
    public boolean checkFields() {
        return false;
    }

    @Override
    public void initData(Object object) {
        product = (Product) object;
    }

    @Override
    public void fillDialog() {
        productName.setText(product.getName());
        productDesc.setText(product.getDescription());
        productPrice.setText(Integer.toString(product.getPrice()));
        numOfCal.setText(Integer.toString(product.getCalories()));
        if (product.isAllergic()) {
            isAllergic.setText("Да");
        } else {
            isAllergic.setText("Нет");
        }
        productCat.setText(product.getCategory().getName());
    }
}
