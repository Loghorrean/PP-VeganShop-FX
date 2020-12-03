package ru.loghorrean.veganShop.controllers.dialogControllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.CategoriesData;
import ru.loghorrean.veganShop.models.ProductsData;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;
import ru.loghorrean.veganShop.models.database.entities.Product;
import ru.loghorrean.veganShop.models.database.entities.ProductCategory;
import ru.loghorrean.veganShop.util.validators.Validator;

import java.sql.SQLException;

public class ProductController extends DialogController implements IFill, IInit {
    @FXML
    private TextField prodName;

    @FXML
    private TextArea prodDesc;

    @FXML
    private TextField prodAmount;

    @FXML
    private TextField prodPrice;

    @FXML
    private TextField prodCal;

    @FXML
    private ComboBox<ProductCategory> prodCat;

    @FXML
    private RadioButton allergicTrue;

    @FXML
    private RadioButton allergicFalse;

    private Product product;

    private boolean isAllergic = true;

    private ProductsData data;

    @Override
    public void initialize() {
        data = ProductsData.getInstance();
        prodCat.setItems(FXCollections.observableArrayList(CategoriesData.getInstance().getCategories()));
        prodCat.getSelectionModel().selectFirst();
    }

    @Override
    public void initData(DatabaseEntity product) {
        this.product = (Product) product;
    }

    @Override
    public void fillDialog() {
        prodName.setText(product.getName());
        prodDesc.setText(product.getDescription());
        prodAmount.setText(Float.toString(product.getAmount()));
        prodPrice.setText(Integer.toString(product.getPrice()));
        prodCal.setText(Integer.toString(product.getCalories()));
        prodCat.setValue(product.getCategory());
        isAllergic = product.isAllergic();
        if (isAllergic) {
            allergicTrue.setSelected(true);
        } else {
            allergicFalse.setSelected(true);
        }
    }

    @Override
    public boolean checkFields() {
        if (!Validator.validateAllFields(prodName.getText(), prodDesc.getText(), prodAmount.getText(), prodPrice.getText(), prodCal.getText())) {
            setMistake("Все поля должны быть заполнены");
            return false;
        }
        boolean prodExists = data.checkIfProductExists(prodName.getText());
        if (product != null) {
            if (prodExists && !prodName.getText().equals(product.getName())) {
                setMistake("Продукт с таким именем уже существует");
                return false;
            }
        } else {
            if (prodExists) {
                setMistake("Продукт с таким именем уже существует");
                return false;
            }
        }
        if (!Validator.checkForInt(prodCal.getText())) {
            setMistake("Калории могут быть только числом");
            return false;
        }
        if (!Validator.checkForFloat(prodAmount.getText())) {
            setMistake("Количество может быть только числом");
            return false;
        }
        return true;
    }

    public void addProduct() {
        try {
            Product product =
                    new Product.ProductBuilder()
                    .withName(prodName.getText())
                    .withDescription(prodDesc.getText())
                    .withAmount(Float.parseFloat(prodAmount.getText()))
                    .withPrice(Integer.parseInt(prodPrice.getText()))
                    .withCalories(Integer.parseInt(prodCal.getText()))
                    .withAllergic(isAllergic)
                    .withCategory(prodCat.getValue())
                    .build();
            data.addProductToModel(product);
            setSuccess("Продукт успешно добавлен");
        } catch (SQLException e) {
            System.out.println("ERROR WHILE ADDING PRODUCT");
            e.printStackTrace();
        }
    }

    public void updateProduct() {
        try {
            product.setName(prodName.getText());
            product.setDescription(prodDesc.getText());
            product.setAmount(Float.parseFloat(prodAmount.getText()));
            product.setPrice(Integer.parseInt(prodPrice.getText()));
            product.setCalories(Integer.parseInt(prodCal.getText()));
            product.setAllergic(isAllergic);
            product.setCategory(prodCat.getValue());
            data.updateProductInModel(product);
            setSuccess("Продукт успешно обновлен");
        } catch (SQLException e) {
            System.out.println("ERROR WHILE UPDATING PRODUCT");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleToggleYesAction(ActionEvent event) {
        isAllergic = true;
    }

    @FXML
    private void handleToggleNoAction(ActionEvent event) {
        isAllergic = false;
    }
}
