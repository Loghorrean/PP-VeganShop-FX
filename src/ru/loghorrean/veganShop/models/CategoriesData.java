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
        manager = new ProductCategoriesManager();
        setCategories();
    }

    public void setCategories() throws SQLException {
        categories = manager.getAll();
    }

    public List<ProductCategory> getCategories() {
        return this.categories;
    }

    public void addCategoryToModel(ProductCategory category) throws SQLException {
        manager.insert(category);
        categories.add(category);
    }

    public void updateCategoryInModel(ProductCategory category) throws SQLException {
        manager.update(category);
    }

    public void deleteCategoryInModel(ProductCategory category) throws SQLException {
        manager.delete(category);
        categories.remove(category);
    }

    public static void unsetModel() {
        instance = null;
    }
}
