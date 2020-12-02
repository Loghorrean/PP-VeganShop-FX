package ru.loghorrean.veganShop.controllers.adminControllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import ru.loghorrean.veganShop.controllers.AdminControllerWithList;
import ru.loghorrean.veganShop.models.TemplatesData;
import ru.loghorrean.veganShop.models.database.entities.DishTemplate;
import ru.loghorrean.veganShop.util.DialogCreator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class AdminTemplatesController extends AdminControllerWithList {
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private VBox VBoxInfo;

    @FXML
    private ListView<DishTemplate> templatesList;

    @FXML
    private TextArea templateInfo;

    @FXML
    private ContextMenu templateContextMenu;

    private TemplatesData data;

    @Override
    public void initialize() {
        try {
            data = TemplatesData.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mainBorderPane.setRight(getUserMenu());

        mainBorderPane.setTop(getAdminMenu("Добавить шаблон", mainBorderPane));

        templateContextMenu = new ContextMenu();

        MenuItem deleteTemplate = new MenuItem("Удалить шаблон");
        deleteTemplate.setOnAction(actionEvent -> {
            DishTemplate template = templatesList.getSelectionModel().getSelectedItem();
            openDeleteTemplateDialog(template);
        });

        MenuItem updateTemplate = new MenuItem("Изменить шаблон");
        updateTemplate.setOnAction(actionEvent -> {
            DishTemplate template = templatesList.getSelectionModel().getSelectedItem();
            openUpdateTemplateDialog(template);
        });

        templateContextMenu.getItems().setAll(deleteTemplate, updateTemplate);

        templatesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DishTemplate>() {
            @Override
            public void changed(ObservableValue<? extends DishTemplate> observableValue, DishTemplate oldValue, DishTemplate newValue) {
                if (newValue != null) {
                    DishTemplate template = templatesList.getSelectionModel().getSelectedItem();
                    templateInfo.setText(template.getDescription());
                }
            }
        });

        templatesList.setItems(FXCollections.observableArrayList(data.getTemplates()));
        templatesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        templatesList.getSelectionModel().selectFirst();

        templatesList.setCellFactory(new Callback<ListView<DishTemplate>, ListCell<DishTemplate>>() {
            @Override
            public ListCell<DishTemplate> call(ListView<DishTemplate> dishTemplateListView) {
                ListCell<DishTemplate> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(DishTemplate template, boolean empty) {
                        super.updateItem(template, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(template.getName());
                        }
                    }
                };
                cell.emptyProperty().addListener(
                        (obs, wasEmpty, nowEmpty) -> {
                            if (nowEmpty) {
                                cell.setContextMenu(null);
                            } else {
                                cell.setContextMenu(templateContextMenu);
                            }
                        }
                );
                return cell;
            }
        });
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
    public void openAddDialog() {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("TemplateDialog")
                        .createDialog("Дрбавить шаблон", mainBorderPane)
                        .addButtons(ButtonType.OK, ButtonType.CANCEL)
                        .addController()
                        .addValidationToButton(ButtonType.OK)
                        .onSuccess("addTemplate")
                        .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            templatesList.setItems(FXCollections.observableArrayList(data.getTemplates()));
        }
    }

    private void openDeleteTemplateDialog(DishTemplate template) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Удалить категорию");
        alert.setHeaderText("Удалить" + template.getName());
        alert.setContentText("Вы уверены? Нажмите OK для подветрждения, или CANCEL для отмены");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                data.deleteTemplateInModel(template);
                templatesList.setItems(FXCollections.observableArrayList(data.getTemplates()));
                templatesList.getSelectionModel().selectFirst();
                setSuccess("Категория удалена");
            } catch (SQLException e) {
                System.out.println("ERROR WHILE DELETING CATEGORY");
                e.printStackTrace();
            }
        }
    }

    private void openUpdateTemplateDialog(DishTemplate template) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("TemplateDialog")
                    .createDialog("Обновить шаблон", mainBorderPane)
                    .addButtons(ButtonType.OK, ButtonType.CANCEL)
                    .addController()
                    .passObject(template)
                    .fillDialog()
                    .addValidationToButton(ButtonType.OK)
                    .onSuccess("updateTemplate")
                    .build();
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            templatesList.setItems(FXCollections.observableArrayList(data.getTemplates()));
        }
    }
}
