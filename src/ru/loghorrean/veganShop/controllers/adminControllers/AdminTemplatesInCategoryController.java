package ru.loghorrean.veganShop.controllers.adminControllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ru.loghorrean.veganShop.controllers.AdminControllerWithList;
import ru.loghorrean.veganShop.models.CategoriesData;
import ru.loghorrean.veganShop.models.CategoriesForTemplatesData;
import ru.loghorrean.veganShop.models.TemplatesData;
import ru.loghorrean.veganShop.models.database.entities.CategoriesForTemplate;
import ru.loghorrean.veganShop.models.database.entities.DishTemplate;
import ru.loghorrean.veganShop.models.database.entities.ProductCategory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class AdminTemplatesInCategoryController extends AdminControllerWithList<DishTemplate> {
    @FXML
    private GridPane testGrid;

    @FXML
    private Button saveButton;

    @FXML
    private Button redirectToCategories;

    private List<ProductCategory> categories;

    private CategoriesForTemplatesData model;

    private HashMap<ProductCategory, Boolean> map;

    private void setHashMap(List<ProductCategory> categories) {
        for (ProductCategory category: categories) {
            map.put(category, false);
        }
    }

    @Override
    public void openAddDialog(ActionEvent event) {

    }

    @Override
    public void initialize() {
        model = CategoriesForTemplatesData.getInstance();
        map = new HashMap<>();
        saveButton.setVisible(false);
        testGrid.setVisible(false);

        mainBorderPane.setRight(getUserMenu());
        mainBorderPane.setBottom(getBackButton());

        categories = CategoriesData.getInstance().getCategories();

        mainListView.setItems(FXCollections.observableArrayList(TemplatesData.getInstance().getTemplates()));
        mainListView.getSelectionModel().selectedItemProperty().addListener((observableValue, template, chosenTemplate) ->  {
            setHashMap(categories);
            saveButton.setVisible(true);
            testGrid.setVisible(true);
            saveButton.setDisable(true);
            if (chosenTemplate != null) {
                int i = 0;
                for (ProductCategory category: categories) {
                    testGrid.add(new Label(category.getName()), 0, i);
                    CheckBox checkBox = new CheckBox();
                    if (chosenTemplate.getCategories().contains(category)) {
                        checkBox.setSelected(true);
                        map.put(category, true);
                    }

                    checkBox.setOnAction(event -> {
                        if (checkBox.isSelected()) {
                            map.put(category, true);
                        } else {
                            map.put(category, false);
                        }
                        saveButton.setDisable(false);
                    });

                    testGrid.add(checkBox, 1, i);
                    i++;
                }
            }
        });
    }

    @FXML
    public void saveChanges(ActionEvent event) {
        DishTemplate template = mainListView.getSelectionModel().getSelectedItem();
        for (ProductCategory category: categories) {
            boolean isPressed = map.get(category);
            if (isPressed) {
                if (!model.checkIfLinkExists(category, template)) {
                    try {
                        model.addLink(new CategoriesForTemplate(category, template));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (model.checkIfLinkExists(category, template)) {
                    try {
                        model.removeLink(model.findLink(category, template));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            redirect(event, "admin/AdminTemplatesInCategoryWindow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToCategories(ActionEvent event) {
        try {
            redirect(event, "admin/AdminCategoriesInTemplateWindow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
