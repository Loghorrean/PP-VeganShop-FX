package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.ProductCategory;
import ru.loghorrean.veganShop.models.database.managers.ProductCategoriesManager;

import java.sql.SQLException;
import java.util.List;

public class CategoriesData {
    private static CategoriesData instance;
    private List<ProductCategory> categories;
    private ProductCategoriesManager manager;

    public static CategoriesData getInstance() throws SQLException {
        if (instance == null) {
            instance = new CategoriesData();
        }
        return instance;
    }

    private CategoriesData() throws SQLException {
        manager = ProductCategoriesManager.getInstance();
        setCategories();
    }

    public void setCategories() throws SQLException {
        categories = manager.getCategories();
    }

    public List<ProductCategory> getCategories() {
        return this.categories;
    }

    public void addCategoryToModel(ProductCategory category) throws SQLException {
        manager.insertCategory(category);
        categories.add(category);
    }

    public void updateCategoryInModel(ProductCategory category) throws SQLException {
        manager.updateCategory(category);
    }

    public void deleteCategoryInModel(ProductCategory category) throws SQLException {
        manager.deleteCategory(category.getId());
        categories.remove(category);
    }

    public static void unsetModel() {
        instance = null;
    }
}
