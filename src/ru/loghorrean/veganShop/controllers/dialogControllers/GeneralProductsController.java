package ru.loghorrean.veganShop.controllers.dialogControllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.CategoriesData;
import ru.loghorrean.veganShop.models.ProductsData;
import ru.loghorrean.veganShop.models.ProductsInGeneralDishesData;
import ru.loghorrean.veganShop.models.database.entities.*;
import ru.loghorrean.veganShop.util.validators.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneralProductsController extends DialogController implements IInit, IFill {
    @FXML
    private ComboBox<ProductCategory> prodCat;

    @FXML
    private ComboBox<Product> prod;

    @FXML
    private TextField prodAmount;

    private GeneralDish dish;

    private ProductInGeneralDish link;

    private ProductsInGeneralDishesData model;

    @Override
    public void fillDialog() {
        prodAmount.setText(Float.toString(link.getAmount()));
        ProductCategory category = link.getProduct().getCategory();
        prodCat.getSelectionModel().select(category);
        List<Product> products = new ArrayList<>(category.getProductsOfCategory());
        prod.setItems(FXCollections.observableArrayList(products));
        prod.getSelectionModel().select(link.getProduct());
        prod.setDisable(true);
        prodCat.setDisable(true);
    }

    @Override
    public void initData(DatabaseEntity object) {
        if (object instanceof GeneralDish) {
            dish = (GeneralDish) object;
        } else if (object instanceof ProductInGeneralDish) {
            link = (ProductInGeneralDish) object;
        }
    }

    @Override
    public void initialize() {
        model = ProductsInGeneralDishesData.getInstance();
        prod.setDisable(true);
        CategoriesData categoriesModel = CategoriesData.getInstance();
        ProductsData productsModel = ProductsData.getInstance();
        prodCat.setItems(FXCollections.observableArrayList(categoriesModel.getCategories()));
        prodCat.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue.getProductsOfCategory().isEmpty()) {
                prod.setDisable(true);
            } else {
                prod.setDisable(false);
                List<Product> products = new ArrayList<>(newValue.getProductsOfCategory());
                prod.setItems(FXCollections.observableArrayList(products));
            }
        });
    }

    @Override
    public boolean checkFields() {
        if (prod.getSelectionModel().getSelectedItem() == null) {
            setMistake("Вы не выбрали продукт");
            return false;
        }
        if (!Validator.validateAllFields(prodAmount.getText())) {
            setMistake("Вы не написали количество продукта");
            return false;
        }
        if (!Validator.checkForFloat(prodAmount.getText())) {
            setMistake("Количество может быть только числовым значением");
            return false;
        }
        return true;
    }

    public void addLink() {
        ProductInGeneralDish link = new ProductInGeneralDish(
                dish,
                prod.getValue(),
                Float.parseFloat(prodAmount.getText())
        );
        try {
            model.addLinkToModel(link);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setSuccess("Продукт успешно добавлен в блюдо");
    }

    public void updateLink() {
        link.setAmount(Float.parseFloat(prodAmount.getText()));
        try {
            model.updateLinkInModel(link);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setSuccess("Связь успешно обновлена");
    }
}
