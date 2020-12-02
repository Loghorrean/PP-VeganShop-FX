package ru.loghorrean.veganShop.controllers.adminControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import ru.loghorrean.veganShop.controllers.AdminControllerWithGrid;
import ru.loghorrean.veganShop.models.ProductsData;
import ru.loghorrean.veganShop.models.database.entities.Product;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.sql.SQLException;
import java.util.List;

public class AdminProductsController extends AdminControllerWithGrid {
    private ProductsData data;

    @Override
    public void initialize() {
        mainBorderPane.setRight(getUserMenu());
        mainBorderPane.setTop(getAdminMenu("Добавить продукт", mainBorderPane));
        data = ProductsData.getInstance();
        List<Product> products = data.getProducts();
        System.out.println(products.size());
        for(int i = 1; i < products.size() + 1; ++i) {
            fillGridRow(i, products.get(i - 1));
        }
    }

    private void fillGridRow(int row, Product product) {
        mainGridPane.add(new Label(Integer.toString(product.getId())), 0, row);
        mainGridPane.add(new Label(product.getName()), 1, row);
        mainGridPane.add(new Label(Float.toString(product.getAmount())), 2, row);
        Button detailsButton = new Button("Детали");
        detailsButton.setOnAction(event -> {
            showDetails(product);
        });
        mainGridPane.add(detailsButton, 3, row);
        Button deleteButton = new Button("Удалить");
        deleteButton.setOnAction(event -> {
            openDeleteDialog(product);
        });
        mainGridPane.add(deleteButton, 4, row);
        Button updateButton = new Button("Обновить информацию");
        updateButton.setOnAction(event -> {
            openUpdateDialog(product);
        });
        mainGridPane.add(updateButton, 5, row);
    }

    @Override
    public void openAddDialog() {

    }

    private void showDetails(Product product) {
        //TODO: add the dialog with additional information
//        Dialog<ButtonType> = new DialogCreator.DialogBuilder()
    }

    private void openDeleteDialog(Product product) {

    }

    private void openUpdateDialog(Product product) {

    }
}
