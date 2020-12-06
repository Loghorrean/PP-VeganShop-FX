package ru.loghorrean.veganShop.controllers.dialogControllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;
import ru.loghorrean.veganShop.models.database.entities.GeneralDish;

public class GeneralDishController extends DialogController implements IFill, IInit {
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
    private Button products;

    private GeneralDish currentDish;

    @Override
    public void initialize() {
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
    }

    @Override
    public void initData(DatabaseEntity object) {
        currentDish = (GeneralDish) object;
        products.setVisible(true);
    }

    @Override
    public boolean checkFields() {
        return false;
    }
}
