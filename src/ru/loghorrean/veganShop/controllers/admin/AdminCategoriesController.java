package ru.loghorrean.veganShop.controllers.admin;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import ru.loghorrean.veganShop.controllers.AdminControllerWithList;
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
    private ContextMenu catContextMenu;

    @FXML
    private Button goToLinksWindow;

    private CategoriesData data;

    @Override
    public void initialize() {
        setPanes("Добавить категорию");
        data = CategoriesData.getInstance();

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

        mainListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                ProductCategory category = mainListView.getSelectionModel().getSelectedItem();
                catInfo.setText(category.getDescription());
            }
        });

        mainListView.setItems(FXCollections.observableArrayList(data.getCategories()));
        mainListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        mainListView.getSelectionModel().selectFirst();

        mainListView.setCellFactory(new Callback<>() {
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
    }

    @Override
    public void openAddDialog(ActionEvent event) {
        Dialog<ButtonType> dialog = new DialogCreator.DialogBuilder("adminDialogs/CategoryDialog")
                .createDialog("Добавление категории", mainBorderPane)
                .addButtons(ButtonType.OK, ButtonType.CANCEL)
                .addController()
                .addValidationToButton(ButtonType.OK)
                .onSuccess("addCategory")
                .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            setSuccess("Категория успешно добавлена");
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

    public void goToLinksWindow(ActionEvent event) {
        try {
            redirect(event, "AdminCategoriesInTemplate");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
