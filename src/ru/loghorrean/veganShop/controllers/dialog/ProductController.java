package ru.loghorrean.veganShop.controllers.dialog;

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
import java.util.Arrays;
import java.util.List;

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

    @FXML
    private ComboBox<String> prodUnit;

    private Product product;

    private List<String> units;

    private boolean isAllergic = false;

    private ProductsData data;

    @Override
    public void initialize() {
        units = Arrays.asList("Кг", "Л", "Шт");
        data = ProductsData.getInstance();
        prodCat.setItems(FXCollections.observableArrayList(CategoriesData.getInstance().getCategories()));
        prodCat.getSelectionModel().selectFirst();
        prodUnit.setItems(FXCollections.observableArrayList(units));
        prodUnit.getSelectionModel().selectFirst();
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
        for (String unit: units) {
            if (product.getUnits().equals(unit)) {
                prodUnit.getSelectionModel().select(unit);
            }
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
        if (Integer.parseInt(prodPrice.getText()) < 1 && Float.parseFloat(prodAmount.getText()) < 1) {
            setMistake("Цена и количество не могут быть меньше нуля");
            return false;
        }
        return true;
    }

    public void addProduct(ActionEvent event) {
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
                            .withUnits(prodUnit.getValue())
                    .build();
            data.addProductToModel(product);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            product.setUnits(prodUnit.getValue());
            data.updateProductInModel(product);
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
