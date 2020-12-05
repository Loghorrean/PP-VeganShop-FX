package ru.loghorrean.veganShop.controllers.adminControllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

public class AdminCategoriesInTemplateController extends AdminControllerWithList<ProductCategory> {
    @FXML
    private Button saveButton;

    @FXML
    private GridPane testGrid;

    private List<DishTemplate> templates;

    private CategoriesForTemplatesData model;

    private HashMap<DishTemplate, Boolean> map;

    @Override
    public void openAddDialog(ActionEvent event) {

    }

    private void setHashMap(List<DishTemplate> templates) {
        for(DishTemplate template: templates) {
            map.put(template, false);
        }
    }

    @Override
    public void initialize() {
        model = CategoriesForTemplatesData.getInstance();
        map = new HashMap<>();
        saveButton.setDisable(true);

        mainBorderPane.setBottom(getBackButton());
        mainBorderPane.setRight(getUserMenu());

        templates = TemplatesData.getInstance().getTemplates();

        mainListView.setItems(FXCollections.observableArrayList(CategoriesData.getInstance().getCategories()));
        mainListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, currentCategory) -> {
            setHashMap(templates);
            if (currentCategory != null) {
                int i = 0;
                for(DishTemplate template: templates) {
                    testGrid.add(new Label(template.getName()), 0, i);

                    CheckBox checkBox = new CheckBox();

                    if (currentCategory.getTemplates().contains(template)) {
                        checkBox.setSelected(true);
                        map.put(template, true);
                    }

                    checkBox.setOnAction(event -> {
                        if (checkBox.isSelected()) {
                            map.put(template, true);
                        } else {
                            map.put(template, false);
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
        ProductCategory category = mainListView.getSelectionModel().getSelectedItem();
        for (DishTemplate template: templates) {
            boolean isPressed = map.get(template);
            if (isPressed) {
                if (!model.checkIfLinkExists(category, template)) {
                    try {
                        model.addLink(new CategoriesForTemplate(category, template));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("CREATE A NEW LINK BETWEEN " + category.getName() + " AND " + template.getName());
                }
            } else {
                if (model.checkIfLinkExists(category, template)) {
                    try {
                        model.removeLink(model.findLink(category, template));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("DESTROY AN EXISTING LINK BETWEEN " + category.getName() + " AND " + template.getName());
                }
            }
        }
        try {
            redirect(event, "admin/AdminCategoriesInTemplateWindow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
