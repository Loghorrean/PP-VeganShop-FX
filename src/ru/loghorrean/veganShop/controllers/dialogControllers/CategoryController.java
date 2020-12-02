package ru.loghorrean.veganShop.controllers.dialogControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.exceptions.CategoryException;
import ru.loghorrean.veganShop.models.CategoriesData;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;
import ru.loghorrean.veganShop.models.database.entities.ProductCategory;
import ru.loghorrean.veganShop.models.database.managers.ProductCategoriesManager;
import ru.loghorrean.veganShop.util.validators.Validator;

import java.sql.SQLException;

public class CategoryController extends DialogController implements IFill, IInit {
    @FXML
    private TextField catName;

    @FXML
    private TextArea catDesc;

    private ProductCategory category;

    private CategoriesData data;

    @Override
    public void initData(DatabaseEntity entity) {
        category = (ProductCategory) entity;
    }

    @Override
    public void initialize() {
        try {
            data = CategoriesData.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fillDialog() {
        catName.setText(category.getName());
        catDesc.setText(category.getDescription());
    }

    @Override
    public boolean checkFields() {
        if (!Validator.validateAllFields(catName.getText(), catDesc.getText())) {
            setMistake("Все поля должны быть заполнены");
            return false;
        }
        boolean catExists = data.checkIfCatExists(catName.getText());
        if (category != null) {
            if (catExists && !catName.getText().equals(category.getName())) {
                setMistake("Категория с таким именем уже существует");
                return false;
            }
        } else {
            if (catExists) {
                setMistake("Категория с таким именем уже существует");
                return false;
            }
        }
        return true;
    }

    public void updateCategory() {
        try {
            category.setName(catName.getText());
            category.setDescription(catDesc.getText());
            data.updateCategoryInModel(category);
        } catch (SQLException e) {
            System.out.println("ERROR WHILE UPDATING CATEGORY");
            e.printStackTrace();
        }
    }

    public void addCategory() {
        try {
            data.addCategoryToModel(new ProductCategory(catName.getText(), catDesc.getText()));
        } catch (SQLException e) {
            System.out.println("ERROR WHILE ADDING A NEW CATEGORY");
            e.printStackTrace();
        }
    }
}
