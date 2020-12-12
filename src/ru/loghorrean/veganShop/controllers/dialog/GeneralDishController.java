package ru.loghorrean.veganShop.controllers.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.GeneralDishesData;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;
import ru.loghorrean.veganShop.models.database.entities.GeneralDish;
import ru.loghorrean.veganShop.util.validators.Validator;

import java.sql.SQLException;

public class GeneralDishController extends DialogController implements IFill, IInit {
    private GeneralDishesData model;

    @FXML
    private TextField dishName;

    @FXML
    private TextArea dishDesc;

    @FXML
    private Slider timeToCook;

    @FXML
    private Button increment;

    @FXML
    private Button decrement;

    @FXML
    private Label currentMins;

    @FXML
    private TextField prodCosts;

    private GeneralDish currentDish;

    @Override
    public void initialize() {
        model = GeneralDishesData.getInstance();
        updateMinsLabel(timeToCook.valueProperty().intValue());
        timeToCook.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            decrement.setDisable(false);
            increment.setDisable(false);
            int value = newValue.intValue();
            updateMinsLabel(value);
            if (value == 0) {
                decrement.setDisable(true);
            }
            if (value == 120) {
                increment.setDisable(true);
            }
        });

        increment.setOnAction(event -> {
            int value = timeToCook.valueProperty().intValue();
            timeToCook.valueProperty().setValue(value + 1);
        });

        decrement.setOnAction(event -> {
            int value = timeToCook.valueProperty().intValue();
            timeToCook.valueProperty().setValue(value - 1);
        });
    }

    private void updateMinsLabel(int minutes) {
        currentMins.setText(Integer.toString(minutes));
    }

    @Override
    public void fillDialog() {
        dishName.setText(currentDish.getName());
        dishDesc.setText(currentDish.getDescription());
        timeToCook.valueProperty().setValue(currentDish.getTimeToCook());
        prodCosts.setText(Integer.toString(currentDish.getProdCosts()));
    }

    @Override
    public void initData(DatabaseEntity object) {
        currentDish = (GeneralDish) object;
    }

    @Override
    public boolean checkFields() {
        if (!Validator.validateAllFields(dishName.getText(), dishDesc.getText())) {
            setMistake("Все поля должны быть заполнены");
            return false;
        }
        if (timeToCook.valueProperty().intValue() == 0) {
            setMistake("Время не может быть нулем...");
            return false;
        }
        boolean dishExists = model.checkIfDishExists(dishName.getText());
        if (currentDish != null) {
            if (dishExists && !currentDish.getName().equals(dishName.getText())) {
                setMistake("Блюдо с таким именем уже существует");
            }
        } else {
            if (dishExists) {
                setMistake("Блюдо с таким названием уже существует");
                return false;
            }
        }
        return true;
    }

    public void addDish() {
        try {
            GeneralDish dish = new GeneralDish(
                    dishName.getText(),
                    dishDesc.getText(),
                    timeToCook.valueProperty().intValue(),
                    Integer.parseInt(prodCosts.getText())
            );
            model.addDish(dish);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateDish() {
        try {
            currentDish.setName(dishName.getText());
            currentDish.setDescription(dishDesc.getText());
            currentDish.setTimeToCook(timeToCook.valueProperty().intValue());
            currentDish.setProdCosts(Integer.parseInt(prodCosts.getText()));
            model.updateDish(currentDish);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
