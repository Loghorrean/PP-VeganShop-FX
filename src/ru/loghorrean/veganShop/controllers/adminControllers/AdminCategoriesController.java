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
import ru.loghorrean.veganShop.controllers.UserController;
import ru.loghorrean.veganShop.models.CategoriesData;
import ru.loghorrean.veganShop.models.database.entities.ProductCategory;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class AdminCategoriesController extends UserController {
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private VBox vboxInfo;

    @FXML
    private ListView<ProductCategory> catList;

    @FXML
    private TextArea catInfo;

    @FXML
    private Button productButton;

    @FXML
    private ContextMenu catContextMenu;

    @FXML
    private Button backButton;

    private CategoriesData data;

    @Override
    public void initialize() {
        try {
            data = CategoriesData.getInstance();

            mainBorderPane.setRight(getUserMenu());

            catContextMenu = new ContextMenu();

            MenuItem deleteCat = new MenuItem("Удалить");
            deleteCat.setOnAction(actionEvent -> {
                ProductCategory category = catList.getSelectionModel().getSelectedItem();
                openDeleteCatDialog(category);
            });

            MenuItem updateCat = new MenuItem("Обновить категорию");
            updateCat.setOnAction(actionEvent -> {
                ProductCategory category = catList.getSelectionModel().getSelectedItem();
                openUpdateCatDialog(category);
            });
            catContextMenu.getItems().addAll(deleteCat, updateCat);

            catList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductCategory>() {
                @Override
                public void changed(ObservableValue<? extends ProductCategory> observableValue, ProductCategory oldValue, ProductCategory newValue) {
                    if (newValue != null) {
                        ProductCategory category = catList.getSelectionModel().getSelectedItem();
                        catInfo.setText(category.getDescription());
                        productButton.setText("Продукты категории " + category.getName());
                        productButton.setOnAction(event -> {
                            openProductDialog(category);
                        });
                        productButton.setVisible(true);
                    }
                }
            });

            catList.setItems(FXCollections.observableArrayList(data.getCategories()));
            catList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            catList.getSelectionModel().selectFirst();

            catList.setCellFactory(new Callback<ListView<ProductCategory>, ListCell<ProductCategory>>() {
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
//        Dialog<ButtonType> dialog = new DialogCreator.DialogBuilder("ProductsInCategoryDialog")
//                                    .createDialog("Продукты в этой категории", mainBorderPane)
//                                    .addButtons(ButtonType.OK)
//                                    .build();
//        dialog.showAndWait();
        System.out.println(category.getName());
    }

    @FXML
    public void backToTheMenu(ActionEvent event) throws IOException {
        redirect(event, "admin/AdminMenuWindow");
    }

    @FXML
    public void getInfo() {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("HowToUseDialog")
                        .createDialog("Как использовать", mainBorderPane)
                        .addButtons(ButtonType.OK)
                        .build();
        dialog.showAndWait();
    }

    @FXML
    private void openAddDialog() {
        Dialog<ButtonType> dialog = new DialogCreator.DialogBuilder("CategoryDialog")
                                .createDialog("Добавление категории", mainBorderPane)
                                .addButtons(ButtonType.OK, ButtonType.CANCEL)
                                .addController()
                                .addValidationToButton(ButtonType.OK)
                                .onSuccess("addCategory")
                                .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            catList.setItems(FXCollections.observableArrayList(data.getCategories()));
        }
    }

    private void openDeleteCatDialog(ProductCategory category) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Удалить категорию");
        alert.setHeaderText("Удалить" + category.getName());
        alert.setContentText("Вы уверены? Нажмите OK для подветрждения, или CANCEL для отмены");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            catList.setItems(FXCollections.observableArrayList(data.getCategories()));
            setSuccess("Категория удалена");
        }
    }

    private void openUpdateCatDialog(ProductCategory category) {
        Dialog<ButtonType> dialog = new DialogCreator.DialogBuilder("CategoryDialog")
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
            catList.setItems(FXCollections.observableArrayList(data.getCategories()));
            setSuccess("Категория обновлена");
        }
    }
}
