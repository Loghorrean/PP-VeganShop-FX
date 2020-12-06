package ru.loghorrean.veganShop.controllers.adminControllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import ru.loghorrean.veganShop.controllers.AdminControllerWithGrid;
import ru.loghorrean.veganShop.models.GeneralDishesData;
import ru.loghorrean.veganShop.models.database.entities.GeneralDish;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.util.List;

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
        moreButton.setOnAction(event -> {
            openMorePage(event, dish);
        });
        mainGridPane.add(moreButton, 2, row);
        Button updateButton = new Button("Редактировать");
        updateButton.setOnAction(event -> {
            openUpdateDialog(event, dish);
        });
        mainGridPane.add(updateButton, 3, row);
        Button deleteButton = new Button("Удалить");
        deleteButton.setOnAction(event -> {
            openDeleteDialog(event, dish);
        });
        mainGridPane.add(deleteButton, 4, row);
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
        dialog.showAndWait();
    }


    private void openMorePage(ActionEvent event, GeneralDish dish) {
        System.out.println(dish.getName());
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
        dialog.showAndWait();
    }

    private void openDeleteDialog(ActionEvent event, GeneralDish dish) {
        System.out.println("deleting " + dish.getName());
    }
}
