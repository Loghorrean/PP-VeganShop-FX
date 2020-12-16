package ru.loghorrean.veganShop.controllers.dialog;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.loghorrean.veganShop.CurrentCustomDish;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.database.entities.DishTemplate;
import ru.loghorrean.veganShop.models.database.entities.Product;
import ru.loghorrean.veganShop.models.database.entities.ProductCategory;
import ru.loghorrean.veganShop.util.validators.Validator;

public class ProductsToCustomController extends DialogController implements IInit {
    @FXML
    private ComboBox<ProductCategory> prodCat;

    @FXML
    private ComboBox<Product> product;

    @FXML
    private TextField prodAmount;

    @FXML
    private TextArea prodRecipe;

    private CurrentCustomDish customDish;

    @Override
    public void initialize() {

    }

    @Override
    public void initData(Object object) {
        customDish = (CurrentCustomDish) object;
        DishTemplate template = customDish.getTemplate();
        prodCat.setItems(FXCollections.observableArrayList(template.getCategories()));
        prodCat.valueProperty().addListener((observableValue, category, t1) -> {
            if (!t1.getProductsOfCategory().isEmpty()) {
                product.setItems(FXCollections.observableArrayList(t1.getProductsOfCategory()));
            }
        });
    }

    @Override
    public boolean checkFields() {
        if (product.getValue() == null) {
            setMistake("Продукт не выбран");
            return false;
        }
        if (!Validator.validateAllFields(prodAmount.getText(), prodRecipe.getText())) {
            setMistake("Все поля должны быть заполнены");
            return false;
        }
        if (!Validator.checkForFloat(prodAmount.getText())) {
            setMistake("Только числовые значения");
            return false;
        }
        if (Integer.parseInt(prodAmount.getText()) < 0) {
            setMistake("Больше нуля нада");
            return false;
        }
        return true;
    }

    public void addProductToDish() {
        customDish.addToComposition(product.getValue(), Integer.parseInt(prodAmount.getText()), prodRecipe.getText());
    }
}
