package ru.loghorrean.veganShop.controllers.adminControllers;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import ru.loghorrean.veganShop.controllers.AdminControllerWithGrid;
import ru.loghorrean.veganShop.models.ProductsData;
import ru.loghorrean.veganShop.models.database.entities.Product;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminProductsController extends AdminControllerWithGrid {
    private ProductsData data;

    @Override
    public void initialize() {
        setPanes("Добавить продукт");
        data = ProductsData.getInstance();
        setGrid();
    }

    private void setGrid() {
        mainGridPane.getChildren().removeAll();
        List<Product> products = data.getProducts();
        for(int i = 1; i < products.size() + 1; ++i) {
            fillGridRow(i, products.get(i - 1));
        }
    }

    private void fillGridRow(int row, Product product) {
        mainGridPane.add(new Label(Integer.toString(product.getId())), 0, row);
        mainGridPane.add(new Label(product.getName()), 1, row);
        mainGridPane.add(new Label(Float.toString(product.getAmount())), 2, row);
        Button detailsButton = new Button("Детали");
        detailsButton.setOnAction(event -> showDetails(product));
        mainGridPane.add(detailsButton, 3, row);
        Button deleteButton = new Button("Удалить");
        deleteButton.setOnAction(event -> openDeleteDialog(event, product));
        mainGridPane.add(deleteButton, 4, row);
        Button updateButton = new Button("Обновить информацию");
        updateButton.setOnAction(event -> openUpdateDialog(event, product));
        mainGridPane.add(updateButton, 5, row);
    }

    @Override
    public void openAddDialog(ActionEvent event) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("adminDialogs/ProductDialog")
                        .createDialog("Добавьте продукт", mainBorderPane)
                        .addButtons(ButtonType.OK, ButtonType.CANCEL)
                        .addController()
                        .addValidationToButton(ButtonType.OK)
                        .onSuccess("addProduct")
                        .redirectsFrom(event)
                        .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            setSuccess("Продукт успешно добавлен");
            setGrid();
        }
    }

    private void openUpdateDialog(ActionEvent event, Product product) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("adminDialogs/ProductDialog")
                        .createDialog("Изменить продукт", mainBorderPane)
                        .addButtons(ButtonType.OK, ButtonType.CANCEL)
                        .addController()
                        .passObject(product)
                        .fillDialog()
                        .addValidationToButton(ButtonType.OK)
                        .onSuccess("updateProduct")
                        .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                setSuccess("Продукт успешно обновлен");
                redirect(event, "admin/AdminProductsWindow");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showDetails(Product product) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("ProductAdditionalInfoDialog")
                        .createDialog("Информация о продукте", mainBorderPane)
                        .addButtons(ButtonType.OK)
                        .addController()
                        .passObject(product)
                        .fillDialog()
                        .build();
        dialog.showAndWait();
    }

    private void openDeleteDialog(ActionEvent event, Product product) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Удалить категорию");
        alert.setHeaderText("Удалить" + product.getName());
        alert.setContentText("Вы уверены? Нажмите OK для подветрждения, или CANCEL для отмены");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                data.deleteProductInModel(product);
                setSuccess("Продукт " + product.getName() + " удален");
                redirect(event, "admin/AdminProductsWindow");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
