package ru.loghorrean.veganShop.controllers.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import ru.loghorrean.veganShop.controllers.AdminControllerWithGrid;
import ru.loghorrean.veganShop.models.GeneralDishesData;
import ru.loghorrean.veganShop.models.database.entities.GeneralDish;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminDishesController extends AdminControllerWithGrid {
    private GeneralDishesData model;

    @Override
    public void initialize() {
        setPanes("Новое блюдо");
        model = GeneralDishesData.getInstance();
        setGrid();
    }

    private void setGrid() {
        List<GeneralDish> dishes = model.getDishes();
        for (int i = 1; i < dishes.size() + 1; ++i) {
            fillGridRow(i, dishes.get(i - 1));
        }
    }

    private void fillGridRow(int row, GeneralDish dish) {
        mainGridPane.add(new Label(Integer.toString(dish.getId())), 0, row);
        mainGridPane.add(new Label(dish.getName()), 1, row);
        Button moreButton = new Button("Подробнее");
        moreButton.setOnAction(event -> openMorePage(event, dish));
        mainGridPane.add(moreButton, 2, row);
        Button updateButton = new Button("Редактировать");
        updateButton.setOnAction(event -> openUpdateDialog(event, dish));
        mainGridPane.add(updateButton, 3, row);
        Button productsButton = new Button("Посмотреть состав");
        productsButton.setOnAction(event -> goToProductsWindow(event, dish));
        mainGridPane.add(productsButton, 4, row);
        Button deleteButton = new Button("Удалить");
        deleteButton.setOnAction(event -> openDeleteDialog(event, dish));
        mainGridPane.add(deleteButton, 5, row);
    }

    @Override
    public void openAddDialog(ActionEvent event) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("adminDialogs/GeneralDishDialog")
                                .createDialog("Новое блюдо", mainBorderPane)
                                .addButtons(ButtonType.OK, ButtonType.CANCEL)
                                .addController()
                                .addValidationToButton(ButtonType.OK)
                                .onSuccess("addDish")
                                .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            setSuccess("Блюдо успешно добавлено");
            setGrid();
        }
    }


    private void openMorePage(ActionEvent event, GeneralDish dish) {
        try {
            redirectWithSmth(event, "mainScreens/DishComposition", dish);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openUpdateDialog(ActionEvent event, GeneralDish dish) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("adminDialogs/GeneralDishDialog")
                                .createDialog("Изменение блюда", mainBorderPane)
                                .addButtons(ButtonType.OK, ButtonType.CANCEL)
                                .addController()
                                .passObject(dish)
                                .fillDialog()
                                .addValidationToButton(ButtonType.OK)
                                .onSuccess("updateDish")
                                .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            setSuccess("Блюдо успешно обновлено");
            try {
                redirect(event, "AdminDishes");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openDeleteDialog(ActionEvent event, GeneralDish dish) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Удалить блюдо");
        alert.setHeaderText("Удалить" + dish.getName());
        alert.setContentText("Вы уверены? Нажмите OK для подветрждения, или CANCEL для отмены");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                model.removeDish(dish);
                setSuccess("Блюдо " + dish.getName() + " удален");
                redirect(event, "AdminDishes");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void goToProductsWindow(ActionEvent event, GeneralDish dish) {
        try {
            redirectWithSmth(event, "AdminProductsInGeneralDish", dish);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
