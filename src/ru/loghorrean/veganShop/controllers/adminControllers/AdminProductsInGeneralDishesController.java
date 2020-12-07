package ru.loghorrean.veganShop.controllers.adminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;
import ru.loghorrean.veganShop.controllers.AdminControllerWithGrid;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;
import ru.loghorrean.veganShop.models.database.entities.GeneralDish;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.io.IOException;

public class AdminProductsInGeneralDishesController extends AdminControllerWithGrid implements IInit, IFill {
    private GeneralDish dish;

    @FXML
    private HBox bottomHBox;

    @FXML
    private Button dishesBackButton;

    @Override
    public void fillDialog() {

    }

    @Override
    public void initData(DatabaseEntity object) {
        dish = (GeneralDish) object;
    }

    @Override
    public void openAddDialog(ActionEvent event) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("adminDialogs/GeneralProductsDialog")
                            .createDialog("Добавьте новый продукт в блюдо", mainBorderPane)
                            .addButtons(ButtonType.OK, ButtonType.CANCEL)
                            .addController()
                            .passObject(dish)
                            .fillDialog()
                            .addValidationToButton(ButtonType.OK)
                            .onSuccess("addProductToDish")
                            .build();
        dialog.showAndWait();
    }

    @Override
    public void initialize() {
        mainBorderPane.setTop(getAdminMenu("Добавить продукт", mainBorderPane));
        mainBorderPane.setRight(getUserMenu());
        bottomHBox.getChildren().add(getBackButton());
    }

    @FXML
    public void backToDishes(ActionEvent event) {
        try {
            redirect(event, "admin/AdminDishesWindow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
