package ru.loghorrean.veganShop.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.loghorrean.veganShop.Cart;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.GeneralInOrderData;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;
import ru.loghorrean.veganShop.models.database.entities.GeneralDish;
import ru.loghorrean.veganShop.util.validators.Validator;

public class GeneralToOrderController extends DialogController implements IFill, IInit {
    private GeneralInOrderData model;
    private GeneralDish dish;
    private Cart cart;

    @FXML
    private Label dishName;

    @FXML
    private TextField dishAmount;

    @Override
    public void initialize() {
        cart = Cart.getInstance();
        model = GeneralInOrderData.getInstance();
    }

    @Override
    public boolean checkFields() {
        if (dishAmount.getText().isEmpty()) {
            setMistake("Поле не заполнено");
            return false;
        }
        if (!Validator.checkForInt(dishAmount.getText())) {
            setMistake("Можно вводить только числа");
            return false;
        }
        if (Integer.parseInt(dishAmount.getText()) < 1) {
            setMistake("Числа только больше нуля");
            return false;
        }
        return true;
    }

    @Override
    public void fillDialog() {
        dishName.setText(dish.getName());
        if (Cart.getInstance().getGeneralFromCart().containsKey(dish)) {
            dishAmount.setText(Integer.toString(Cart.getInstance().getGeneralFromCart().get(dish)));
        }
    }

    @Override
    public void initData(DatabaseEntity object) {
        dish = (GeneralDish) object;
    }

    public void addGeneralToCart() {
        cart.addGeneralToCart(dish, Integer.parseInt(dishAmount.getText()));
        setSuccess("Блюдо добавлено в корзину");
    }

    public void updateGeneralInCart() {
        cart.updateGeneralInCart(dish, Integer.parseInt(dishAmount.getText()));
        setSuccess("Блюдо в корзине обновлено");
    }
}
