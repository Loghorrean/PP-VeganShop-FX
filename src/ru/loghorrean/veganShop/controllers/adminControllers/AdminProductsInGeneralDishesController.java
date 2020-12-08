package ru.loghorrean.veganShop.controllers.adminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import ru.loghorrean.veganShop.controllers.AdminControllerWithGrid;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.ProductsInGeneralDishesData;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;
import ru.loghorrean.veganShop.models.database.entities.GeneralDish;
import ru.loghorrean.veganShop.models.database.entities.Product;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class AdminProductsInGeneralDishesController extends AdminControllerWithGrid implements IInit, IFill {
    private GeneralDish dish;

    private ProductsInGeneralDishesData model;

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
        int i = 0;
        for (Product product: dish.getProductsInDish()) {
            fillGridRow(i + 1, product);
            i++;
        }
    }

    public void fillGridRow(int row, Product product) {
        mainGridPane.add(new Label(product.getName()), 0, row);
        mainGridPane.add(new Label(Float.toString(product.getAmount())), 1, row);
        Button amountButton = new Button("Изменить количество");
        amountButton.setOnAction(event -> {
            changeAmount(event, product);
        });
        mainGridPane.add(amountButton, 2, row);
        Button deleteButton = new Button("Удалить");
        deleteButton.setOnAction(event -> {
            deleteProductFromTheDish(event, product);
        });
        mainGridPane.add(deleteButton, 3, row);
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

    public void changeAmount(ActionEvent event, Product product) {

    }

    public void deleteProductFromTheDish(ActionEvent event, Product product) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Удалить продукт из блюда?");
        alert.setHeaderText("Удалить" + product.getName());
        alert.setContentText("Вы уверены? Нажмите OK для подветрждения, или CANCEL для отмены");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                model.removeLinkFromModel(product, dish);
                setSuccess("Продукт " + product.getName() + " удален из блюда " + dish.getName());
                redirectWithSmth(event, "admin/AdminProductsInGeneralDishWindow", dish);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize() {
        model = ProductsInGeneralDishesData.getInstance();
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
