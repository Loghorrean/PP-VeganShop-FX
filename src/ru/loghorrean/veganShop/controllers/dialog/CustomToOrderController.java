package ru.loghorrean.veganShop.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.loghorrean.veganShop.Cart;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.database.entities.CustomDish;
import ru.loghorrean.veganShop.util.validators.Validator;

public class CustomToOrderController extends DialogController implements IInit, IFill {
    @FXML
    private TextField dishName;

    @FXML
    private TextField dishNumber;

    private CustomDish dish;

    @Override
    public void initialize() {

    }

    @Override
    public boolean checkFields() {
        if (!Validator.validateAllFields(dishNumber.getText())) {
            setMistake("Поля не заполнены");
            return false;
        }
        if (!Validator.checkForInt(dishNumber.getText())) {
            setMistake("Только числовые значения");
            return false;
        }
        if (Integer.parseInt(dishNumber.getText()) < 0) {
            setMistake("Значение должно быть больше нуля");
            return false;
        }
        return true;
    }

    @Override
    public void fillDialog() {
        dishName.setText(dish.getName());
    }

    @Override
    public void initData(Object object) {
        dish = (CustomDish) object;
    }

    public void addCustomToCart() {
        Cart.getInstance().addCustomToCart(dish, Integer.parseInt(dishNumber.getText()));
        setSuccess("Блюдо добавлено в корзину");
    }
}
