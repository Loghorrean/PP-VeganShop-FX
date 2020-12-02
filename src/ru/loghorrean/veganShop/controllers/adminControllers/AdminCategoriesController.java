package ru.loghorrean.veganShop.controllers.adminControllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import ru.loghorrean.veganShop.controllers.AdminControllerWithList;
import ru.loghorrean.veganShop.controllers.UserController;
import ru.loghorrean.veganShop.models.CategoriesData;
import ru.loghorrean.veganShop.models.database.entities.ProductCategory;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class AdminCategoriesController extends AdminControllerWithList<ProductCategory> {
    @FXML
    private VBox vboxInfo;

    @FXML
    private TextArea catInfo;

    @FXML
    private Button productButton;

    @FXML
    private ContextMenu catContextMenu;

    private CategoriesData data;

    @Override
    public void initialize() {
        try {
            data = CategoriesData.getInstance();

            mainBorderPane.setRight(getUserMenu());

            mainBorderPane.setTop(getAdminMenu("Добавить категорию", mainBorderPane));

            catContextMenu = new ContextMenu();

            MenuItem deleteCat = new MenuItem("Удалить");
            deleteCat.setOnAction(actionEvent -> {
                ProductCategory category = mainListView.getSelectionModel().getSelectedItem();
                openDeleteCatDialog(category);
            });

            MenuItem updateCat = new MenuItem("Обновить категорию");
            updateCat.setOnAction(actionEvent -> {
                ProductCategory category = mainListView.getSelectionModel().getSelectedItem();
                openUpdateCatDialog(category);
            });
            catContextMenu.getItems().addAll(deleteCat, updateCat);

            mainListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductCategory>() {
                @Override
                public void changed(ObservableValue<? extends ProductCategory> observableValue, ProductCategory oldValue, ProductCategory newValue) {
                    if (newValue != null) {
                        ProductCategory category = mainListView.getSelectionModel().getSelectedItem();
                        catInfo.setText(category.getDescription());
                        productButton.setText("Продукты категории " + category.getName());
                        productButton.setOnAction(event -> {
                            openProductDialog(category);
                        });
                        productButton.setVisible(true);
                    }
                }
            });

            mainListView.setItems(FXCollections.observableArrayList(data.getCategories()));
            mainListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            mainListView.getSelectionModel().selectFirst();

            mainListView.setCellFactory(new Callback<ListView<ProductCategory>, ListCell<ProductCategory>>() {
                @Override
                public ListCell<ProductCategory> call(ListView<ProductCategory> productCategoryListView) {
                    ListCell<ProductCategory> cell = new ListCell<>() {
                        @Override
                        protected void updateItem(ProductCategory category, boolean empty) {
                            super.updateItem(category, empty);
                            if (empty) {
                                setText(null);
                            } else {
                                setText(category.getName());
                            }
                        }
                    };
                    cell.emptyProperty().addListener(
                            (obs, wasEmpty, nowEmpty) -> {
                                if (nowEmpty) {
                                    cell.setContextMenu(null);
                                } else {
                                    cell.setContextMenu(catContextMenu);
                                }
                            }
                    );

                    return cell;
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openProductDialog(ProductCategory category) {
        //TODO: make product dialog to view products in the category
//        Dialog<ButtonType> dialog = new DialogCreator.DialogBuilder("ProductsInCategoryDialog")
//                                    .createDialog("Продукты в этой категории", mainBorderPane)
//                                    .addButtons(ButtonType.OK)
//                                    .build();
//        dialog.showAndWait();
        System.out.println(category.getName());
    }

    @Override
    public void openAddDialog() {
        Dialog<ButtonType> dialog = new DialogCreator.DialogBuilder("adminDialogs/CategoryDialog")
                .createDialog("Добавление категории", mainBorderPane)
                .addButtons(ButtonType.OK, ButtonType.CANCEL)
                .addController()
                .addValidationToButton(ButtonType.OK)
                .onSuccess("addCategory")
                .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            mainListView.setItems(FXCollections.observableArrayList(data.getCategories()));
        }
    }

    private void openDeleteCatDialog(ProductCategory category) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Удалить категорию");
        alert.setHeaderText("Удалить" + category.getName());
        alert.setContentText("Вы уверены? Нажмите OK для подветрждения, или CANCEL для отмены");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                data.deleteCategoryInModel(category);
                mainListView.setItems(FXCollections.observableArrayList(data.getCategories()));
                mainListView.getSelectionModel().selectFirst();
                setSuccess("Категория удалена");
            } catch (SQLException e) {
                System.out.println("ERROR WHILE DELETING CATEGORY");
                e.printStackTrace();
            }
        }
    }

    private void openUpdateCatDialog(ProductCategory category) {
        Dialog<ButtonType> dialog = new DialogCreator.DialogBuilder("adminDialogs/CategoryDialog")
                            .createDialog("Изменение категории", mainBorderPane)
                            .addButtons(ButtonType.OK, ButtonType.CANCEL)
                            .addController()
                            .passObject(category)
                            .fillDialog()
                            .addValidationToButton(ButtonType.OK)
                            .onSuccess("updateCategory")
                            .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            mainListView.setItems(FXCollections.observableArrayList(data.getCategories()));
            setSuccess("Категория обновлена");
        }
    }
}
