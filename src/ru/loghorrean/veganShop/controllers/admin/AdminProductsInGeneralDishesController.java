package ru.loghorrean.veganShop.controllers.admin;

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
import ru.loghorrean.veganShop.models.database.entities.ProductInGeneralDish;
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
    public void initData(Object object) {
        dish = (GeneralDish) object;
        setGrid();
    }

    private void setGrid() {
        int i = 0;
        for (Product product: dish.getProductsInDish()) {
            ProductInGeneralDish link = ProductsInGeneralDishesData.getInstance().getLink(product, dish);
            fillGridRow(i + 1, link);
            i++;
        }
    }

    private void fillGridRow(int row, ProductInGeneralDish link) {
        mainGridPane.add(new Label(link.getProduct().getName()), 0, row);
        mainGridPane.add(new Label(link.getAmount() + " " + link.getProduct().getUnits()), 1, row);
        Button amountButton = new Button("Изменить количество");
        amountButton.setOnAction(event -> {
            changeAmount(event, link);
        });
        mainGridPane.add(amountButton, 2, row);
        Button deleteButton = new Button("Удалить");
        deleteButton.setOnAction(event -> {
            deleteProductFromTheDish(event, link);
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
                            .addValidationToButton(ButtonType.OK)
                            .onSuccess("addLink")
                            .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            setGrid();
        }
    }

    public void changeAmount(ActionEvent event, ProductInGeneralDish link) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("adminDialogs/GeneralProductsDialog")
                            .createDialog("Измените связь", mainBorderPane)
                            .addButtons(ButtonType.OK, ButtonType.CANCEL)
                            .addController()
                            .passObject(link)
                            .fillDialog()
                            .addValidationToButton(ButtonType.OK)
                            .onSuccess("updateLink")
                            .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                redirectWithSmth(event, "AdminProductsInGeneralDish", dish);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteProductFromTheDish(ActionEvent event, ProductInGeneralDish link) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Удалить продукт из блюда?");
        alert.setHeaderText("Удалить" + link.getProduct().getName() + " из блюда?");
        alert.setContentText("Вы уверены? Нажмите OK для подветрждения, или CANCEL для отмены");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                model.removeLinkFromModel(link);
                setSuccess("Продукт " + link.getProduct().getName() + " удален из блюда " + dish.getName());
                redirectWithSmth(event, "AdminProductsInGeneralDish", dish);
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
            redirect(event, "AdminDishes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
